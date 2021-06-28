package com.sn.dsi.ldapauth;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPResponse;
import com.novell.ldap.LDAPResponseQueue;
import com.novell.ldap.connectionpool.PoolManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@SpringBootApplication
public class LdapAuthApplication {

	private static PoolManager poolManager;

	private static LDAPConnection connection;


	public static void main(String[] args) {
		SpringApplication.run(LdapAuthApplication.class, args);
	}


	public static boolean authentifier(String login, String password) {
		String ldapHost = "LDAP://orange-sonatel.com";
		String ldapDomaine = "@orange-sonatel.com" + "\\" + login;

		System.out.println("####### LdapHost : " + ldapHost);
		System.out.println("####### LdapDomaine : " + ldapDomaine);

		boolean resultat = false;
		try {
			System.out.println("####### Connexion LDAP");
			connection.connect(ldapHost, LDAPConnection.DEFAULT_PORT);
			System.out.println("####### Connexion LDAP OK");

			System.out.println("####### Authentification LDAP");
			LDAPResponseQueue queue = connection.bind(LDAPConnection.LDAP_V3, ldapDomaine, password.getBytes("UTF8"), (LDAPResponseQueue) null);
			LDAPResponse lDAPResponse = (LDAPResponse) queue.getResponse();

			if (lDAPResponse.getResultCode() == LDAPException.SUCCESS) {
				resultat = true;
				System.out.println("####### Authentification LDAP OK");
			} else {
				System.out.println("####### Authentification LDAP KO");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return resultat;
	}

	public static void close() {
		try {
			connection.disconnect();
			System.out.println("######## Close Connection ");
		} catch (Throwable e) {
			e.printStackTrace(System.out);
		}
	}


	private static boolean isAuthentifierAgentLDAP(String login, String password) {

		String providerUrl = "LDAP://orange-sonatel.com";

		String securityPrincipal = "@orange-sonatel.com";

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, providerUrl);

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, login + securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, password);

		long duree = System.currentTimeMillis();
		System.out.println("#######  InitialDirContext debut Demande LDAP ");


		try {
			DirContext context = new InitialDirContext(env);

			duree = System.currentTimeMillis() - duree;
			System.out.println("#######  InitialDirContext fin Demande LDAP ");
			System.out.println("######## Resultat LDAP : OK ");
			System.out.println("######## Duree LDAP : " + duree + "ms  ");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	private boolean isAgentAuthentifyByLDAP(String login, String password) {

		String providerUrl = "LDAP://orange-sonatel.com";

		String securityPrincipal = "@orange-sonatel.com";

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, providerUrl);

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, login + securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, password);

		long duree = System.currentTimeMillis();
		System.out.println("#######  InitialDirContext debut Demande LDAP ");


		try {
			DirContext context = new InitialDirContext(env);

			duree = System.currentTimeMillis() - duree;
			System.out.println("#######  InitialDirContext fin Demande LDAP ");
			System.out.println("######## Resultat LDAP : OK");
			System.out.println("######## Duree LDAP : " + duree + "ms  ");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

