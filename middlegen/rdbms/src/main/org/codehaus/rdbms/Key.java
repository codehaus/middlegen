package org.codehaus.rdbms;

import java.util.List;

/**
 *
 * @author <a href="mailto:aslak.hellesoy at netcom.no">Aslak Helles&oslash;y</a>
 * @version $Revision: 1.1 $
 */
public interface Key {
    List getColumns();

    /**
     * Returns true if this key is compound, i.e. if there are more than one columns in the key.
     *
     * @return The Compound value
     */
    boolean isCompound();

    Table getTable();

    boolean isNullable();
}
