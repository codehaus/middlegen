package org.codehaus.rdbms.impl;

import org.codehaus.rdbms.MutableTable;
import org.codehaus.rdbms.TableFactory;
import org.nanocontainer.multicast.MulticastInvoker;
import org.nanocontainer.multicast.MulticasterFactory;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoIntrospectionException;
import org.picocontainer.PicoRegistrationException;
import org.picocontainer.defaults.DefaultPicoContainer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of TableFactory that returns a {@link java.lang.reflect.Proxy}
 * instance of a {@link org.codehaus.rdbms.Table}. This proxy can implement additional interfaces.
 * This is particularly handy if you want to "augment" the feature set of
 * the Table objects. The internal implementation uses
 * <a href="http://www.picocontainer.org/">PicoContainer<a/>.
 *
 * @author Aslak Helles&oslash;y
 * @version $Revision: 1.1 $
 * @see org.codehaus.rdbms.impl.PicoColumnFactory
 */
public class PicoTableFactory implements TableFactory {
    private final Set mixinClasses = new HashSet();
    private final Set mixinObjects = new HashSet();
    private final MutablePicoContainer pluginContainer;
    private final Map tables = new HashMap();

    public PicoTableFactory(MutablePicoContainer pluginContainer) throws PicoIntrospectionException, PicoRegistrationException {
        this.pluginContainer = pluginContainer;
    }

    public void addTableMixin(Class clazz) {
        mixinClasses.add(clazz);
    }

    public void addTableMixin(Object o) {
        mixinObjects.add(o);
    }

    public MutableTable createTable() {
        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.setParent(pluginContainer);

        // Register the basic components
        pico.registerComponentInstance(this);
        pico.registerComponentImplementation(TableImpl.class);

        // Register the mixin classes.
        for (Iterator iterator = mixinClasses.iterator(); iterator.hasNext();) {
            pico.registerComponentImplementation((Class) iterator.next());
        }

        // Register the mixin objects.
        for (Iterator iterator = mixinObjects.iterator(); iterator.hasNext();) {
            pico.registerComponentInstance(iterator.next());
        }

        Object proxy = new MulticasterFactory().createComponentMulticaster(null,
                null,
                pico.getComponentInstances(),
                false,
                new MulticastInvoker());

        return (MutableTable) proxy;
    }

    public final MutableTable getTable(String sqlName) {
        if (sqlName == null) {
            throw new NullPointerException("sqlName can't be null");
        }
        return (MutableTable) tables.get(sqlName);
    }

    public MutableTable createTable(String sqlName) {
        MutableTable result = createTable();
        tables.put(sqlName, result);
        return result;
    }

    public Collection getTables() {
        return tables.values();
    }
}
