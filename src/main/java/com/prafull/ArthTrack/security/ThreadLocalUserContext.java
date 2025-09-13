package com.prafull.ArthTrack.security;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUserContext {
    
    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();
    
    /**
     * Set the current user ID (called by JwtAuthFilter)
     */
    public static void setCurrentUserId(Long userId) {
        currentUserId.set(userId);
    }
    
    /**
     * Get the current user ID (super fast - just reads from ThreadLocal)
     */
    public static Long getCurrentUserId() {
        return currentUserId.get();
    }
    
    /**
     * Clear the current user ID (called after request completes)
     */
    public static void clear() {
        currentUserId.remove();
    }
}
