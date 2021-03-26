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
  int hudx = 0;
  int hudy = (1080-165);
  int hp = 45; // 1-15, 16-30, 31-45;

  public class Floor {

    public Floor(final GroupLayer Floorlayer, float x, float y, boolean status) {
      String imgPath = "aaa";
      if (status) {
      imgPath = "images/floorSmall.png";
    } else {
      imgPath = "images/smallmarine.png";
    };
      Image image = plat.assets().getImage(imgPath);
      final ImageLayer layer = new ImageLayer(image);
      layer.setOrigin(ImageLayer.Origin.UL);
      Floorlayer.addAt(layer, x, y);
    }
  }


  public class Unit {//разработка

    public Unit(final GroupLayer Floorlayer, float x, float y, boolean status) {
      String imgPath = "aaa";
      if (status) {
      imgPath = "images/floorSmall.png";
    } else {
      imgPath = "images/smallmarine.png";
    };
      Image image = plat.assets().getImage(imgPath);
      final ImageLayer layer = new ImageLayer(image);
      layer.setOrigin(ImageLayer.Origin.UL);
      Floorlayer.addAt(layer, x, y);
    }
  }



 public class UnitInfo
{
     public UnitInfo(final GroupLayer Hudlayer, float x, float y, int hp)
     {
       String imgPath = "images/Hud.png"; // путь до картинки
       String imgPathFace = "Face";
       if (hp >30){
         imgPathFace = "images/Face1.png";
       } else {
         if (hp >15)
         {
           imgPathFace = "images/Face2.png";
         }
         else {
           imgPathFace = "images/Face3.png";
         }
       }


       Image image = plat.assets().getImage(imgPath); // создаем объект картинка по этому пути
       Image imageface = plat.assets().getImage(imgPathFace);
       final ImageLayer layer = new ImageLayer(image); // создаем слой с картинкой на основе этой картинки
       final ImageLayer layerface = new ImageLayer(imageface);
       layer.setOrigin(ImageLayer.Origin.UL); // объекту layer устанавливается место отрисовки верхний левый угол
       layerface.setOrigin(ImageLayer.Origin.UL);
       Hudlayer.addAt(layer, x, y);
       Hudlayer.addAt(layerface, x+15, y+15);
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
    final GroupLayer Floorlayer = new GroupLayer();
    rootLayer.add(Floorlayer);

    int numList[][]=new int[masx][masy];
    for (int i = ii; i < masx; i++) {
      for (int j = jj; j < masy; j++) {
        numList[i][j]=0;
          new Floor(Floorlayer, i*floorw, j*floorh, true);
      }
    }



    // create a group layer to hold the peas
    final GroupLayer Hudlayer = new GroupLayer();
    rootLayer.add(Hudlayer);
    new UnitInfo(Hudlayer, hudx, hudy, hp);


    // when the pointer is tapped/clicked, add a new pea
    pointer.events.connect(new Slot<Pointer.Event>() {
      @Override public void onEmit (Pointer.Event event) {
        if (event.kind.isStart) {
          String x=String.valueOf(event.x());
          String y=String.valueOf(event.y());
          System.out.printf("x: %s \t y: %s   \n", x, y);
          System.out.printf("hp: %d \n", hp);

          hp -= 5;
          new UnitInfo(Hudlayer, hudx, hudy, hp);


          for (int i = ii; i < masx; i++) {
            for (int j = jj; j < masy; j++) {
              if ( (event.x() >= i*floorw) && (event.x() <= (i+1)*floorw) && (event.y() >= j*floorh) && (event.y() <= (j+1)*floorh))
              {  new Floor(Floorlayer, i*floorw, j*floorh, false);}
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
