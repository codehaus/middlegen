package org.codehaus.rdbms.jdbc;

import org.generama.AbstractPlugin;
import org.generama.MetadataProvider;
import org.generama.WriterMapper;

import java.net.URL;

/**
 * Compatibility test for RdbmsTableFromJdbcMetadataProvider. This is a "black box test".
 * This test relies on the following system properties being defined:
 *
 * <ul>
 *   <li>middlegen.database.driver : Class name of the jdbc driver</li>
 *   <li>middlegen.database.driver.classpath : Classpath used to load driver</li>
 *   <li>middlegen.database.url : Database connection url</li>
 *   <li>middlegen.database.userid : Database user name</li>
 *   <li>middlegen.database.password : Database password</li>
 *   <li>middlegen.database.schema : Database schema</li>
 *   <li>middlegen.database.catalog : Database catalog</li>
 *   <li>middlegen.database.setup.script : Path to script to create test tables</li>
 *   <li>middlegen.database.teardown.script : Path to script to drop test tables</li>
 * </ul>
 *
 * This test will generate a Torque XML file named compatibilitytest.xml. Torque is not
 * used or needed in any way, we're using the Torque XML format only because it is a
 * handy format.
 *
 * @author Aslak Hellesoy
 * @version $Revision: 1.1 $
 */
public class JDBCDriverCompatibilityTestCase extends DatabaseXMLGeneratingPluginTestCase {

    protected AbstractPlugin createPlugin(MetadataProvider metadataProvider, WriterMapper writerMapper) throws Exception {
        return new JDBCDriverCompatibilityPlugin(
                    metadataProvider,
                    writerMapper
            );
    }

    protected URL getExpected() throws Exception {
        return getResourceRelativeToThisPackage("JDBCDriverCompatibilityTestCase.torque.xml");
    }

}
