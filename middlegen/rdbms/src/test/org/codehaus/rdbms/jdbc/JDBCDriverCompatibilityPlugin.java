package org.codehaus.rdbms.jdbc;

import org.generama.JellyPlugin;
import org.generama.MetadataProvider;
import org.generama.WriterMapper;


/**
 * Plugin that generates torque.xml files.
 * This plugin is in the core only because it is used for compatibility tests.
 *
 * @bean.class name="middlegen-torque"
 *             displayName="Torque"
 *             shortDescription="Generates xml files for torque."
 *
 * @bean.attribute name="xdoclet-class" value="xdoclet.XDoclet"
 *
 * @author Aslak Hellesoy
 * @version $Revision: 1.1 $
 */
public class JDBCDriverCompatibilityPlugin extends JellyPlugin {
    public JDBCDriverCompatibilityPlugin(MetadataProvider metadataProvider, WriterMapper writerMapper) {
        super(metadataProvider, writerMapper);
        setFilereplace("torque.xml");
    }

    public void setWriterMapper(WriterMapper writerMapper) {
        this.writerMapper = writerMapper;
    }

    public void setMetadataProvider(MetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }
}
