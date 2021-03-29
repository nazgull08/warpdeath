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
import playn.core.Keyboard;
import playn.core.Key;
import playn.core.Sound;

import playn.core.Tile;
import react.RFuture;
import react.Slot;
import java.util.ArrayList;
import java.util.List;
import react.UnitSlot;
import static java.lang.Math.max;

public class HelloGame extends SceneGame {

  Unit[] squad = new Unit[10]; //Создаем массив юнитов. Наш отряд. Максимальный объем - 10 юнитов.
  int squadLimit = 4; // Текущий предел отряда

  int selectedUnit = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)
  int selectedUnitx = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)
  int selectedUnity = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)

  int masx = 10;
  int masy = 10;
  int ii = 0;
  int jj = 0;
  int floorw = 32;
  int floorh = 32;
  int hudx = 0;
  int hudy = (1080-165);
  int soundCounter2 = 0;
  int soundCounter3 = 0;
  int soundCoolDown2 = 0;
  int soundCoolDown3 = 0;


  boolean showHUD = true;

  boolean shiftDown, ctrlDown, tabDown;

  Graphics gfx = plat.graphics();

  private boolean loaded;


  Image bgImage = plat.assets().getImage("images/twin.png");
  Image flImage = plat.assets().getImage("images/floorSmall.png");
  Image flActiveImage = plat.assets().getImage("images/floorSmallActive.png");
  Image marineImage = plat.assets().getImage("images/smallmarine.png");
  Image commissionerImage = plat.assets().getImage("images/smallCommissioner.png");
  Image hudImage = plat.assets().getImage("images/Hud.png");
  Image face1Image = plat.assets().getImage("images/Face1.png");
  Image face2Image = plat.assets().getImage("images/Face2.png");
  Image face3Image = plat.assets().getImage("images/Face3.png");
  Image imageM = plat.assets().getImage( "images/menu.png");

  Sound[] marineSounds = new Sound[] {                      // создание и присваивание значений массиву звуков
    plat.assets().getSound("sounds/marineSounds/anytimenow")
  , plat.assets().getSound("sounds/marineSounds/anytimenow")
  , plat.assets().getSound("sounds/marineSounds/anytimenow")
  , plat.assets().getSound("sounds/marineSounds/anytimenow")
  , plat.assets().getSound("sounds/marineSounds/anytimenow")
  };
  Sound[] commissionerSounds = new Sound[] {                      // создание и присваивание значений массиву звуков
    plat.assets().getSound("sounds/commissioner/1000300")
  , plat.assets().getSound("sounds/commissioner/leave")
  , plat.assets().getSound("sounds/commissioner/serve")
  , plat.assets().getSound("sounds/commissioner/shoot")
  , plat.assets().getSound("sounds/commissioner/stand")
  };
  Sound[] landSounds = new Sound[] {                      // создание и присваивание значений массиву звуков
    plat.assets().getSound("sounds/landSounds/blip")
  };

  GroupLayer Floorlayer = new GroupLayer(); //Создаем групповой слой корабля
  GroupLayer Squadlayer = new GroupLayer(); //Создаем групповой слой отряда


  public final void redraw(){
    rootLayer.disposeAll(); //Чистим все слои, принадлежащие корневому слою.
    Floorlayer = new GroupLayer(); //Создаем групповой слой корабля
    Squadlayer = new GroupLayer(); //Создаем групповой слой отряда
    // ----------Background
    // Создаем задник
    ImageLayer bgLayer = new ImageLayer(bgImage);
    bgLayer.setSize(plat.graphics().viewSize);
    rootLayer.add(bgLayer); //Добавляем задник корневому слою

    // ----------Starship

    rootLayer.add(Floorlayer);    //Добавляем слой корабля корневому слою
    int numList[][]=new int[masx][masy];
    for (int i = ii; i < masx; i++) {
      for (int j = jj; j < masy; j++) {
        numList[i][j]=0;
        if(selectedUnit == -1){
          new Floor(Floorlayer, i*floorw, j*floorh,false);    //Заполняем групповой слой корабля тайлами пола
        }
        else {
          if((i == selectedUnitx) && (j == selectedUnity)){
            new Floor(Floorlayer, i*floorw, j*floorh, true);    //Заполняем групповой слой корабля тайлами пола
          }
          else {
            new Floor(Floorlayer, i*floorw, j*floorh, false);    //Заполняем групповой слой корабля тайлами пола
          }
        }
      }
    }

    // ----------Squad
    rootLayer.add(Squadlayer);    //Добавляем слой отряда корневому слою
    for(int i=0; i<squadLimit; i++) { // Заполняем групповой слой отряда фигурками отряда
      if (i == 3){
        new SquadView(Squadlayer, squad[i], i); //Рисуем члена отряда
      }
      else{
        new SquadView(Squadlayer, squad[i], i); //Рисуем члена отряда
      }
    }

    // ----------HUD
    if(showHUD) {
      final GroupLayer Hudlayer = new GroupLayer();
      rootLayer.add(Hudlayer);
      if(selectedUnit==-1){
        new UnitInfo(Hudlayer, hudx, hudy);
      } else {
        new UnitInfo(Hudlayer, hudx, hudy, squad[selectedUnit]);
      }
    };
  };

  public class Unit {//Класс юнита
    public String name, age, role;
    public int hp, energy, mind, morale, hunger, thirst, fireres, electres, bleedres, stunres;
    public int actionPoints;
    public int posx, posy;

    public Unit(String nm, String ag, String rl, int h, int en, int x, int y) { //Конструктор юнита
      name = nm;
      age  = ag;
      role = rl;
      hp   = h;
      energy = en;
      mind = 100;
      morale = 100;
      hunger = 100;
      thirst = 100;
      fireres  = 100;
      electres = 100;
      bleedres = 100;
      stunres  = 100;

      actionPoints = 10;

      posx = x;
      posy = y;
    }

  }

  public class Floor {

    public Floor(final GroupLayer Floorlayer, float x, float y, boolean status) {
      Image image = flImage;
      if (status){
        image = flActiveImage;
      }
      final ImageLayer layer = new ImageLayer(image);
      layer.setOrigin(ImageLayer.Origin.UL);
      Floorlayer.addAt(layer, x, y);
    }
  }

  public class SquadView {

    public SquadView(final GroupLayer Squadlayer, Unit unt, int i) {
      if(i==0)
      {
        final ImageLayer layer = new ImageLayer(marineImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, unt.posx*floorw, unt.posy*floorh);
      }
      if(i==1)
      {
        final ImageLayer layer = new ImageLayer(marineImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, unt.posx*floorw, unt.posy*floorh);
      }
      if(i==2)
      {
        final ImageLayer layer = new ImageLayer(marineImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, unt.posx*floorw, unt.posy*floorh);
      }
      if(i==3)
      {
        final ImageLayer layer = new ImageLayer(commissionerImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, unt.posx*floorw, unt.posy*floorh);
      }

      //final ImageLayer layer = new ImageLayer(marineImage);
    }
  }

  public class Menu {

    public Menu(final GroupLayer Menulayer, float x, float y) {
      final ImageLayer layer = new ImageLayer(imageM);
      layer.setOrigin(ImageLayer.Origin.UL);
      Menulayer.addAt(layer, x+450, y-500);
    }
  }


  public class UnitInfo {
    String fontName = "Impact";  // название шрифта
    Font font = new Font(fontName, Font.Style.PLAIN, 24f); // создаем шрифт, из названия, стиля, размер текста, жирность
    TextFormat format = new TextFormat(font);  // создаем формат из шрифта

    protected Layer createTextLayer(TextLayout layout, int color) { // функция запекания
      Canvas canvas = plat.graphics().createCanvas(layout.size); // берем холст
      canvas.setFillColor(color).fillText(layout, 0, 0); // рисуем на холсте текст
      return new ImageLayer(canvas.toTexture()); // запекаем холст, возвращаем в виде слоя
    }

    public UnitInfo(final GroupLayer Hudlayer, float x, float y, Unit unt) // Коструктор 0, если выбран юнит
    {
      String hps = "hp: " + String.valueOf(unt.hp); // преобразуем число в строку
      String energys = "energy: " + String.valueOf(unt.energy);
      String minds = "mind: " + String.valueOf(unt.mind);
      String morales = "morale: " + String.valueOf(unt.morale);
      String hungers = "hunger: " + String.valueOf(unt.hunger);
      String thirsts = "thirst: " + String.valueOf(unt.thirst);
      String fireress = "fireres: " + String.valueOf(unt.fireres);
      String electress = "electres: " + String.valueOf(unt.electres);
      String bleedress = "bleedres: " + String.valueOf(unt.bleedres);
      String stunress = "stunres: " + String.valueOf(unt.stunres);
      String names = "name: " + unt.name;
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
      TextLayout layoutNames = gfx.layoutText(names, format); // получаем надпись
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
      Layer layerNames = createTextLayer(layoutNames, 0xFFFF8C00); // запекаем надпись в картинку

      Image imageface = face1Image;
      if (unt.hp >30){
        imageface = face1Image;
      } else {
        if (unt.hp >15) {
          imageface = face2Image;
        }
        else {
          imageface = face3Image;
        }
      }

      final ImageLayer layer = new ImageLayer(hudImage); // создаем слой с картинкой на основе этой картинки
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
      Hudlayer.addAt(layerNames, x+410, y+15+24+24+24+24);

    }

    public UnitInfo(final GroupLayer Hudlayer, float x, float y) // Коструктор 1, если никто не выбран
    {
      final ImageLayer layer = new ImageLayer(hudImage); // создаем слой с картинкой на основе этой картинки
      layer.setOrigin(ImageLayer.Origin.UL); // объекту layer устанавливается место отрисовки верхний левый угол
      Hudlayer.addAt(layer, x, y);

    }
  }


  public final Pointer pointer;

  @Override public void update(Clock clock) {
    super.update(clock);
  };

  public HelloGame(Platform plat) {
    super(plat, 25); // 25 millis per frame = ~40fps

    Unit tychus = new Unit("Тайкус","40","Танк",200,100, 3, 5); //Создаем Тайкуса в c координатами 3 5
    Unit raynor = new Unit("Рейнор","40","ДД",150,150, 0, 2); //Создаем Рейнора с координатами 0 2
    Unit ray = new Unit("Ray","40","Medic",75 ,75, 4, 4); //Создаем Рейнора с координатами 0 2
    Unit commissioner = new Unit("Сommissioner","40","Сommissioner",125 ,125, 7, 7); //Создаем Рейнора с координатами 0 2

    squad[0] = tychus; // Добавляем Тайкуса в отряд
    squad[1] = raynor; // Добавляем Рейнора в отряд
    squad[2] = ray; // Добавляем Рейнора в отряд
    squad[3] = commissioner; // Добавляем Рейнора в отряд

    update.connect(new Slot<Clock>() {
      public void onEmit (Clock clock) {
        if (soundCoolDown2 > 0){soundCoolDown2--;};       // уменьшение кд на воспроизведение реплики
        if (soundCoolDown3 > 0){soundCoolDown3--;};       // уменьшение кд на воспроизведение реплики
        redraw();
      }
    });

    // Обработчик клавиатуры
    plat.input().keyboardEvents.connect(new Keyboard.KeySlot() {
      public void onEmit (Keyboard.KeyEvent ev) {
        switch (ev.key) {
          case SHIFT: {
           shiftDown = ev.down;
//           redraw();
           break;
          }
          case CONTROL:{
            ctrlDown = ev.down;
            if (ctrlDown){
              final GroupLayer Menulayer = new GroupLayer();
              rootLayer.add(Menulayer);
              new Menu(Menulayer,hudx, hudy);
            }
  //          redraw();
            break;
          }
          case TAB:{
            tabDown = ev.down;
            if (!tabDown){
              showHUD = !showHUD;
            }
//            redraw();
            break;
          }
          default: break;
        }
      }
    });
    // ...

    // combine mouse and touch into pointer events
    pointer = new Pointer(plat);

    // Обработчик мыши
    pointer.events.connect(new Slot<Pointer.Event>() {


      @Override public void onEmit (Pointer.Event event) {


        if (event.kind.isStart) {
          String x=String.valueOf(event.x());
          String y=String.valueOf(event.y());

          selectedUnit = -1;
          for(int i=0; i<squadLimit;i++){
            if ((event.x() >= squad[i].posx*floorw) && (event.x() <= (squad[i].posx+1)*floorw) && (event.y() >= squad[i].posy*floorh) && (event.y() <= (squad[i].posy+1)*floorh)){
              selectedUnit=i;
              selectedUnity=squad[i].posy;
              selectedUnitx=squad[i].posx;


              if (i == 3){
                    if (soundCoolDown3 <= 0){
                      commissionerSounds[soundCounter3].play();    //воспроизведение звуков
                      soundCounter3++;     //счетчик реплик
                      soundCoolDown3 = 100;   // установка кд реплики
                    }
                    if (soundCounter3 == 5){soundCounter3 = 0;};  // сброс реплик
                  };



              if (i == 2){
                    if (soundCoolDown2 <= 0){
                      marineSounds[soundCounter2].play();    //воспроизведение звуков
                      soundCounter2++;     //счетчик реплик
                      soundCoolDown2 = 100;   // установка кд реплики
                    };
                    if (soundCounter2 == 5){soundCounter2 = 0;};  // сброс реплик
                  };




            }
          };
          if ((selectedUnit== -1) && (event.x() <=10*floorw) && (event.y() <=10*floorh)){
            landSounds[0].play();
          }
        };
      }
    });
  }
}
