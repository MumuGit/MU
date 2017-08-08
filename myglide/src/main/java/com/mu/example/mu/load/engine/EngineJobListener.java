package com.mu.example.mu.load.engine;


import com.mu.example.mu.load.Key;

interface EngineJobListener {

  void onEngineJobComplete(Key key, EngineResource<?> resource);

  void onEngineJobCancelled(EngineJob engineJob, Key key);
}
