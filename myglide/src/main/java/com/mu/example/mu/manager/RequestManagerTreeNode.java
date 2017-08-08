package com.mu.example.mu.manager;


import com.mu.example.mu.RequestManager;

import java.util.Set;

/**
 * Provides access to the relatives of a RequestManager based on the current context. The context
 * hierarchy is provided by nesting in Activity and Fragments; the application context does not
 * provide access to any other RequestManagers hierarchically.
 */
public interface RequestManagerTreeNode {
  /**
   * Returns all descendant {@link RequestManager}s relative to the context of the current
   * {@link RequestManager}.
   */
  Set<RequestManager> getDescendants();
}
