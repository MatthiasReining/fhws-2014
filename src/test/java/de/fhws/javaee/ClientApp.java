package de.fhws.javaee;

import de.fhws.javaee.fhws.RegisterBeanRemote;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthias Reining
 */
public class ClientApp {

    public static void main(String... args) throws NamingException {
        //RegisterBeanRemote rbr = lookupRemoteStatelessRegister();
        //rbr.persist();
        doBeanLookup();

    }

    public static void doBeanLookup() throws NamingException {

        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        // username
        jndiProps.put(Context.SECURITY_PRINCIPAL, "admin");
        // password
        jndiProps.put(Context.SECURITY_CREDENTIALS, "admin");
        // This is an important property to set if you want to do EJB invocations via the remote-naming project
        jndiProps.put("jboss.naming.client.ejb.context", true);
        // create a context passing these properties
        Context ctx = new InitialContext(jndiProps);
        // lookup the bean     Foo
        //ejb:/fhws//RegisterBean!de.fhws.javaee.fhws.RegisterBeanRemote
        //ctx.lookup("java:jboss/exported/java:jboss/exported/helloworld");
        
        RegisterBeanRemote brb = (RegisterBeanRemote) ctx.lookup("fhws/!de.fhws.javaee.fhws.RegisterBean");
        //brb.persist();
        System.out.println("Remote Foo bean returned " );
        ctx.close();
  // after this point the beanRemoteInterface is not longer valid!
    }

    private static RegisterBeanRemote lookupRemoteStatelessRegister() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        //jndiProperties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
        //jndiProperties.put("java.naming.provider.url","jnp://yourServer:1099");
        //jndiProperties.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jnp.interfaces");
        //jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:8080");
        //jndiProperties.put(Context.SECURITY_PRINCIPAL, "jboss");
        //jndiProperties.put(Context.SECURITY_CREDENTIALS, "pwdpwd1!");
        //jndiProperties.put("jboss.naming.client.ejb.context", true);
        //jndiProperties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");

        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "fhws";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = "RegisterBean";
        // the remote view fully qualified class name
        final String viewClassName = RegisterBeanRemote.class.getName();
        // let's do the lookup
        String lookupPath = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
        System.out.println(lookupPath);
        lookupPath = "ejb:/fhws/RegisterBean!de.fhws.javaee.fhws.RegisterBeanRemote";
        lookupPath = "RegisterBean!de.fhws.javaee.fhws.RegisterBeanRemote";
        return (RegisterBeanRemote) context.lookup(lookupPath);
    }

}
