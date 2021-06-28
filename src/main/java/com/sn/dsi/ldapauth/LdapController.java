package com.sn.dsi.ldapauth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

/**
 * Created by tonux on 1/2/19.
 */

@RestController
public class LdapController {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/auth")
    public ResponseEntity<String> auth(@RequestBody Agent agent, HttpServletResponse res) {

        System.out.println("====> authentification LDAP : " + agent.getLogin());
        if (agent == null || agent.getPassword().isEmpty() || agent.getLogin().isEmpty()) {
            return new ResponseEntity<>(
                    "Error",
                    HttpStatus.FORBIDDEN);
        }

        if (!loopAuth(agent)) {
            return new ResponseEntity<>(
                    "Error",
                    HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(
                    "success",
                    HttpStatus.OK);
        }

    }

    private boolean loopAuth(Agent agent) {
        int n = 0;
        boolean result = false;
        while (n < 5 && !result) {

            System.out.println("try : "+agent.getLogin()+ " - "+agent.getPassword());

            if (isAuthentifierAgentLDAP(agent)) {
                return true;
            }

            // wait 2 secondes
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            n++;
        }

        return false;
    }

    private boolean isAuthentifierAgentLDAP(Agent agent) {

        if (agent == null) return false;

        String providerUrl = "LDAP://orange-sonatel.com";

        String securityPrincipal = "@orange-sonatel.com";

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, providerUrl);

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, agent.getLogin() + securityPrincipal);
        env.put(Context.SECURITY_CREDENTIALS, agent.getPassword());

        long duree = System.currentTimeMillis();

        boolean resultat = false;
        try {
            DirContext context = new InitialDirContext(env);
            resultat = true;
        } catch (Exception e) {e.printStackTrace();
        }
        duree = System.currentTimeMillis() - duree;
        return resultat;
    }
}
