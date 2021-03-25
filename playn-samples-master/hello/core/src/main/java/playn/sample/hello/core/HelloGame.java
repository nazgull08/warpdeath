/**
 * Copyright 2011 The PlayN Authors
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

      // для запуска!!!! (mvn test -Pjava)
package playn.sample.hello.core;

import react.Slot;

import playn.core.Clock;
import playn.core.Image;
import playn.core.Platform;
import playn.core.Pointer;
import playn.scene.GroupLayer;
import playn.scene.ImageLayer;
import playn.scene.SceneGame;
import playn.scene.SceneGame;

public class HelloGame extends SceneGame {

  int masx = 10;
  int masy = 10;
  int ii = 0;
  int jj = 0;
  int floorw = 32;
  int floorh = 32;

  public class Pea {
    // choose a semi-random angular velocity
    private final float angVel = (plat.tick() % 20 - 5) / 2000f;

    public Pea(final GroupLayer peaLayer, float x, float y) {
      Image image = plat.assets().getImage("images/floorSmall.png");
      final ImageLayer layer = new ImageLayer(image);
      layer.setOrigin(ImageLayer.Origin.UL);
      peaLayer.addAt(layer, x, y);

      // connect to the paint signal to animate our rotation
      paint.connect(new Slot<Clock>() {
        @Override public void onEmit (Clock clock) {
          layer.setRotation(clock.tick * angVel);
        }
      });
    }
  }


  public class Starship {

    public Starship(final GroupLayer starShiplayer, float x, float y, boolean status) {
      String imgPath = "aaa";
      if (status) {
      imgPath = "images/floorSmall.png";
    } else {
      imgPath = "images/SmallFloorActive.png";
    };
      Image image = plat.assets().getImage(imgPath);
      final ImageLayer layer = new ImageLayer(image);
      layer.setOrigin(ImageLayer.Origin.UL);
      starShiplayer.addAt(layer, x, y);
    }
  }




  public final Pointer pointer;

  public HelloGame(Platform plat) {
    super(plat, 25); // 25 millis per frame = ~40fps

    // combine mouse and touch into pointer events
    pointer = new Pointer(plat);

    // create and add background image layer
    Image bgImage = plat.assets().getImage("images/twin.png");
    ImageLayer bgLayer = new ImageLayer(bgImage);
    bgLayer.setSize(plat.graphics().viewSize);
    rootLayer.add(bgLayer);

    // create a group layer to hold the peas
    final GroupLayer starShiplayer = new GroupLayer();
    rootLayer.add(starShiplayer);

    int numList[][]=new int[masx][masy];
    for (int i = ii; i < masx; i++) {
      for (int j = jj; j < masy; j++) {
        numList[i][j]=0;
          new Starship(starShiplayer, i*floorw, j*floorh, true);
      }
    }

    // create a group layer to hold the peas
    final GroupLayer peaLayer = new GroupLayer();
    rootLayer.add(peaLayer);

    // when the pointer is tapped/clicked, add a new pea
    pointer.events.connect(new Slot<Pointer.Event>() {
      @Override public void onEmit (Pointer.Event event) {
        if (event.kind.isStart) {
          String x=String.valueOf(event.x());
          String y=String.valueOf(event.y());
          System.out.printf("x: %s \t y: %s   \n", x, y);



          for (int i = ii; i < masx; i++) {
            for (int j = jj; j < masy; j++) {
              if ( (event.x() >= i*floorw) && (event.x() <= (i+1)*floorw) && (event.y() >= j*floorh) && (event.y() <= (j+1)*floorh))
              {  new Starship(starShiplayer, i*floorw, j*floorh, false);}
            }
          }
//          new Pea(peaLayer, event.x(), event.y());
          //String s=String.valueOf(event.x());
          //System.out.printf("%s ", s);
        };
      }
    });
  }
}
