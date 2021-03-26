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
import playn.core.Canvas;
import playn.core.Image;
import playn.core.Graphics;
import playn.core.Font;
import playn.core.TextBlock;
import playn.core.TextFormat;
import playn.core.TextLayout;
import playn.core.Platform;
import playn.core.Pointer;
import playn.scene.Layer;
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
  int energy = 10;
  int mind = 100;
  int morale = 600;
  int hunger = 100;
  int thirst = 100;
  int fireres = 100;
  int electres = 100;
  int bleedres = 100;
  int stunres = 100;



  Graphics gfx = plat.graphics();


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
  String fontName = "Impact";  // название шрифта
  Font font = new Font(fontName, Font.Style.PLAIN, 24f); // создаем шрифт, из названия, стиля, размер текста, жирность
  TextFormat format = new TextFormat(font);  // создаем формат из шрифта
  String hps = "hp: " + String.valueOf(hp); // преобразуем число в строку
  String energys = "energy: " + String.valueOf(energy);
  String minds = "mind: " + String.valueOf(mind);
  String morales = "morale: " + String.valueOf(morale);
  String hungers = "hunger: " + String.valueOf(hunger);
  String thirsts = "thirst: " + String.valueOf(thirst);
  String fireress = "fireres: " + String.valueOf(fireres);
  String electress = "electres: " + String.valueOf(electres);
  String bleedress = "bleedres: " + String.valueOf(bleedres);
  String stunress = "stunres: " + String.valueOf(stunres);
  TextLayout layoutHp = gfx.layoutText(hps, format); // получаем надпись
  TextLayout layoutEnergy = gfx.layoutText(energys, format); // получаем надпись
  TextLayout layoutMind = gfx.layoutText(minds, format); // получаем надпись
  TextLayout layoutMorale = gfx.layoutText(morales, format); // получаем надпись
  TextLayout layoutHunger = gfx.layoutText(hungers, format); // получаем надпись
  TextLayout layoutThirst = gfx.layoutText(thirsts, format); // получаем надпись
  TextLayout layoutFireres = gfx.layoutText(fireress, format); // получаем надпись
  TextLayout layoutElectres = gfx.layoutText(electress, format); // получаем надпись
  TextLayout layoutBleedres = gfx.layoutText(bleedress, format); // получаем надпись
  TextLayout layoutStunres = gfx.layoutText(stunress, format); // получаем надпись
  Layer layerHP = createTextLayer(layoutHp, 0xFFFF0000); // запекаем надпись в картинку
  Layer layerEnergy = createTextLayer(layoutEnergy, 0xFFAFEEEE); // запекаем надпись в картинку
  Layer layerMind = createTextLayer(layoutMind, 0xFF000000); // запекаем надпись в картинку
  Layer layerMorale = createTextLayer(layoutMorale, 0xFF0000FF); // запекаем надпись в картинку
  Layer layerHunger = createTextLayer(layoutHunger, 0xFF008000); // запекаем надпись в картинку
  Layer layerThirst = createTextLayer(layoutThirst, 0xFF00FFFF); // запекаем надпись в картинку
  Layer layerFireres = createTextLayer(layoutFireres, 0xFFD2691E); // запекаем надпись в картинку
  Layer layerElectres = createTextLayer(layoutElectres, 0xFFFFFF00); // запекаем надпись в картинку
  Layer layerBleedres = createTextLayer(layoutBleedres, 0xFF8B0000); // запекаем надпись в картинку
  Layer layerStunres = createTextLayer(layoutStunres, 0xFFFF8C00); // запекаем надпись в картинку



  protected Layer createTextLayer(TextLayout layout, int color) { // функция запекания
    Canvas canvas = plat.graphics().createCanvas(layout.size); // берем холст
    canvas.setFillColor(color).fillText(layout, 0, 0); // рисуем на холсте текст
    return new ImageLayer(canvas.toTexture()); // запекаем холст, возвращаем в виде слоя
  }

  public UnitInfo(final GroupLayer Hudlayer, float x, float y, int hp, int energy, int mind, int morale, int hunger, int thirst, int fireres, int electres, int bleedres, int stunres)
  {
    String imgPath = "images/Hud.png"; // путь до картинки
    String imgPathFace = "Face";
    if (hp >30){
      imgPathFace = "images/Face1.png";
    } else {
      if (hp >15) {
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
    Hudlayer.addAt(layerHP, x+220, y+15);
    Hudlayer.addAt(layerEnergy, x+220, y+15+24);
    Hudlayer.addAt(layerMind, x+220, y+15+15+24+10);
    Hudlayer.addAt(layerMorale, x+220, y+15+15+24+10+24);
    Hudlayer.addAt(layerHunger, x+220, y+15+15+24+10+48);
    Hudlayer.addAt(layerThirst, x+220, y+15+15+24+10+48+24);
    Hudlayer.addAt(layerFireres, x+410, y+15);
    Hudlayer.addAt(layerElectres, x+410, y+15+24);
    Hudlayer.addAt(layerBleedres, x+410, y+15+24+24);
    Hudlayer.addAt(layerStunres, x+410, y+15+24+24+24);

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
    new UnitInfo(Hudlayer, hudx, hudy, hp, energy, mind, morale, hunger, thirst, fireres, electres, bleedres, stunres);


    // when the pointer is tapped/clicked, add a new pea
    pointer.events.connect(new Slot<Pointer.Event>() {
      @Override public void onEmit (Pointer.Event event) {
        if (event.kind.isStart) {
          String x=String.valueOf(event.x());
          String y=String.valueOf(event.y());
          System.out.printf("x: %s \t y: %s   \n", x, y);
          System.out.printf("hp: %d \n", hp);

          hp -= 5;
          new UnitInfo(Hudlayer, hudx, hudy, hp, energy, mind, morale, hunger, thirst, fireres, electres, bleedres, stunres); // рисуем сверху худ


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
