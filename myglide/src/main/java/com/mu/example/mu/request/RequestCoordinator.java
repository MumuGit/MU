package com.mu.example.mu.request;

/**
 * An interface for coordinating multiple requests with the same {@link
 * com.mu.example.mu.request.target.Target}.
 */
public interface RequestCoordinator {//协调员

  /**
   * Returns true if the {@link Request} can display a loaded bitmap.
   *
   * @param request The {@link Request} requesting permission to display a bitmap.
   */
  boolean canSetImage(Request request);

  /**
   * Returns true if the {@link Request} can display a placeholder.
   *
   * @param request The {@link Request} requesting permission to display a placeholder.
   */
  boolean canNotifyStatusChanged(Request request);

  /**
   * Returns true if any coordinated {@link Request} has successfully completed.
   *
   * @see Request#isComplete()
   */
  boolean isAnyResourceSet();

  /**
   * Must be called when a request coordinated by this object completes successfully.
   */
  void onRequestSuccess(Request request);
}
