/**
 * Copyright 2010 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.sample.hello.java;

import playn.java.LWJGLPlatform;
import playn.sample.hello.core.HelloGame;

public class HelloGameJava {

  public static void main(String[] args) {
    LWJGLPlatform.Config config = new LWJGLPlatform.Config();
    config.width = 1920;
    config.height = 1080;
    config.appName = "Void Death";
config.fullscreen = true;
//config.fullscreen = false;
    LWJGLPlatform plat = new LWJGLPlatform(config);
    new HelloGame(plat);
    plat.start();
  }
}
