/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.usergrid.corepersistence;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.netflix.config.ConfigurationManager;
import java.io.IOException;
import java.util.Properties;
import org.apache.usergrid.persistence.cassandra.Setup;
import org.apache.usergrid.persistence.cassandra.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.usergrid.persistence.entities.Application;

import org.apache.usergrid.persistence.EntityManagerFactory;
import static org.apache.usergrid.persistence.cassandra.CassandraService.DEFAULT_APPLICATION;
import static org.apache.usergrid.persistence.cassandra.CassandraService.DEFAULT_APPLICATION_ID;
import static org.apache.usergrid.persistence.cassandra.CassandraService.DEFAULT_ORGANIZATION;
import static org.apache.usergrid.persistence.cassandra.CassandraService.MANAGEMENT_APPLICATION;
import static org.apache.usergrid.persistence.cassandra.CassandraService.MANAGEMENT_APPLICATION_ID;
import static org.apache.usergrid.persistence.cassandra.CassandraService.STATIC_APPLICATION_KEYSPACE;
import org.apache.usergrid.persistence.collection.migration.MigrationException;
import org.apache.usergrid.persistence.collection.migration.MigrationManager;


/**
 * Cassandra-specific setup utilities.
 *
 * @author edanuff
 */
public class CpSetup implements Setup {

    private static final Logger logger = LoggerFactory.getLogger( CpSetup.class );

    private final org.apache.usergrid.persistence.EntityManagerFactory emf;
    private final CassandraService cass;

    private GuiceModule gm;


    /**
     * Instantiates a new setup object.
     *
     * @param emf the emf
     */
    public CpSetup( EntityManagerFactory emf, CassandraService cass ) {
        this.emf = emf;
        this.cass = cass;
    }


    /**
     * Initialize.
     *
     * @throws Exception the exception
     */
    @Override
    public synchronized void setup() throws Exception {
        createDefaultApplications();
    }


    @Override
    public void init() throws Exception {
        cass.init();

        try {
            ConfigurationManager.loadCascadedPropertiesFromResources( "corepersistence" );

            Properties testProps = new Properties() {{
                put("cassandra.hosts", "localhost");
                put("cassandra.port", System.getProperty("cassandra.rpc_port"));
            }};
            ConfigurationManager.loadProperties( testProps );
        }
        catch ( IOException e ) {
            throw new RuntimeException( "Cannot do much without properly loading our configuration.", e );
        }

        Injector injector = Guice.createInjector( new GuiceModule() );
        MigrationManager m = injector.getInstance( MigrationManager.class );
        try {
            m.migrate();
        } catch (MigrationException ex) {
            throw new RuntimeException("Error migrating Core Persistence", ex);
        }
    }


    public void createDefaultApplications() throws Exception {
        // TODO unique check?
        emf.initializeApplication( DEFAULT_ORGANIZATION, DEFAULT_APPLICATION_ID, DEFAULT_APPLICATION, null );
        emf.initializeApplication( DEFAULT_ORGANIZATION, MANAGEMENT_APPLICATION_ID, MANAGEMENT_APPLICATION, null );
    }


    /** @return staticly constructed reference to the management application */
    public static Application getManagementApp() {
        return SystemDefaults.managementApp;
    }


    /** @return statically constructed reference to the default application */
    public static Application getDefaultApp() {
        return SystemDefaults.defaultApp;
    }

    @Override
    public void setupSystemKeyspace() throws Exception {
    }

    @Override
    public void setupStaticKeyspace() throws Exception {
    }

    @Override
    public boolean keyspacesExist() {
        return true;
    }

    static class SystemDefaults {
        private static final Application managementApp = new Application( MANAGEMENT_APPLICATION_ID );
        private static final Application defaultApp = new Application( DEFAULT_APPLICATION_ID );
        static {
            managementApp.setName( MANAGEMENT_APPLICATION );
            defaultApp.setName( DEFAULT_APPLICATION );
        }
    }
}
