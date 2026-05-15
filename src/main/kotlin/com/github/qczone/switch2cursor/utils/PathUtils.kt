package com.github.qczone.switch2cursor.utils

object PathUtils {
    /**
     * Normalize path for WSL.
     * Converts //wsl.localhost/Ubuntu-24.04/home/user -> /home/user
     */
    fun normalizePath(path: String?): String {
        if (path == null) return ""
        
        // Handle WSL paths
        if (path.startsWith("//wsl.localhost/") || path.startsWith("//wsl$/") || 
            path.startsWith("\\\\wsl.localhost\\") || path.startsWith("\\\\wsl$\\")) {
            
            val normalizedPath = path.replace('\\', '/')
            val parts = normalizedPath.split('/').filter { it.isNotEmpty() }
            
            if (parts.size >= 2 && (parts[0] == "wsl.localhost" || parts[0] == "wsl$")) {
                // Skip 'wsl.localhost' and the distribution name (e.g., 'Ubuntu-24.04')
                return "/" + parts.drop(2).joinToString("/")
            }
        }
        
        return path
    }
}
