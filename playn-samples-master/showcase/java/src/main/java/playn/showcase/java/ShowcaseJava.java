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
package playn.showcase.java;

import playn.java.LWJGLPlatform;
import playn.showcase.core.Showcase;

public class ShowcaseJava {

  public static void main(String[] args) throws Exception {
    LWJGLPlatform.Config config = new LWJGLPlatform.Config();
    LWJGLPlatform plat = new LWJGLPlatform(config);
    plat.graphics().registerFont("Museo-300", plat.assets().getFont("text/Museo.otf"));
    new Showcase(plat, new Showcase.DeviceService() {
      public String info () {
        Runtime rt = Runtime.getRuntime();
        return "Java [vers=" + System.getProperty("java.version") +
          ", os=" + System.getProperty("os.name") +
          ", osarch=" + System.getProperty("os.arch") +
          ", osvers=" + System.getProperty("os.version") +
          ", freemem=" + (rt.freeMemory()/1024) + "/" + (rt.totalMemory()/1024) + "k" +
          ", maxmem=" + (rt.maxMemory()/1024) + "k]";
      }
    });
    plat.start();
  }
}
