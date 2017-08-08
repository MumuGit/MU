package com.mu.example.mu.manager;

import com.mu.example.mu.RequestManager;

import java.util.Collections;
import java.util.Set;
/**
 * tnormal
 */
/**
 * A {@link RequestManagerTreeNode} that returns no relatives.
 */
final class EmptyRequestManagerTreeNode implements RequestManagerTreeNode {
    @Override
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
