package org.codehaus.rdbms;

import org.codehaus.rdbms.MutableTable;

import java.util.Collection;

/**
 * Factory for creation of {@link org.codehaus.rdbms.Table}s.
 *
 * @author <a href="mailto:aslak.hellesoy at bekk.no">Aslak Helles&oslash;y</a>
 * @version $Revision: 1.1 $
 */
public interface TableFactory {
    /**
     * Creates a new Table object.
     *
     * @return a newly created Table.
     */
    MutableTable createTable(String sqlName);

    Collection getTables();

    MutableTable getTable( String sqlName);

}
