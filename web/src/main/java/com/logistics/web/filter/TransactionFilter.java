package com.logistics.web.filter;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class TransactionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TransactionFilter.class);

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager em = null;
        try {
            utx.begin();
            em = emf.createEntityManager();
            em.joinTransaction();
            chain.doFilter(request, response);
            utx.commit();
        } catch (Exception e) {
            try {
                if (utx != null) {
                    utx.rollback();
                }
            } catch (Exception ex) {
                logger.error("Error during transaction rollback", ex);
            }
            logger.error("Error during request processing", e);
            throw new ServletException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Other filter methods
}