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

import playn.core.Mouse;
import playn.core.Mouse.ButtonEvent;
import playn.core.Mouse.MotionEvent;

import playn.core.Tile;
import react.RFuture;
import react.Slot;
import java.util.ArrayList;
import java.util.List;
import react.UnitSlot;
import static java.lang.Math.max;
import pythagoras.f.IDimension;


public class HelloGame extends SceneGame {

  Unit[] squad = new Unit[10]; //Создаем массив юнитов. Наш отряд. Максимальный объем - 10 юнитов.
  Object[] objectArr = new Object[24*12];
  int squadLimit = 4; // Текущий предел отряда
  int objectLimit = 0; // Текущий предел отряда

  IDimension size = plat.graphics().screenSize();


  int selectedUnit = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)
  int selectedUnitx = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)
  int selectedUnity = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)

  int masx = 10;
  int masy = 10;
  int ii = 0;
  int jj = 0;
  int floorw = 128;
  int floorh = 128;
  int hudx = 0;
  int hudy = (1080-255);
  int soundCounter2 = 0;
  int soundCounter3 = 0;
  int soundCoolDown2 = 0;
  int soundCoolDown3 = 0;
  int timerost = 0;
  int faceCoolDown = 20;
  int selectedTime = 0;
  int selectedTimeCD = 20;
  int selectedTimeI = 1;


  float cameraX = 0;
  float cameraY = 0;

  int shipPositionX = 0;
  int shipPositionY = 0;


  int startdelay = 20;

  boolean showHUD = true;

  boolean animation = true;
  boolean speakAnimation = true;

  boolean keyUpDown, keyDownDown, keyLeftDown, keyRightDown, shiftDown, ctrlDown, tabDown, wDown, sDown, aDown, dDown;

  Graphics gfx = plat.graphics();

  private boolean loaded;

  String movingWay = "none";

  Image bgImage = plat.assets().getImage("images/twin.png");

  Image wallU = plat.assets().getImage("images/floor/wallU.png");
  Image wallR = plat.assets().getImage("images/floor/wallR.png");
  Image wallF = plat.assets().getImage("images/floor/wallF.png");
  Image walFb = plat.assets().getImage("images/floor/wallFb.png");
  Image empty = plat.assets().getImage("images/floor/emptiness.png");
  Image emptyB = plat.assets().getImage("images/floor/emptiness.png");
  Image passUp = plat.assets().getImage("images/floor/passup.png");
  Image passAll = plat.assets().getImage("images/floor/passall.png");
  Image passGor = plat.assets().getImage("images/floor/passgor.png");
  Image passT = plat.assets().getImage("images/floor/passt.png");
  Image passTb = plat.assets().getImage("images/floor/passtb.png");

  Image newwall = plat.assets().getImage("images/floor/newwall.png");
  Image doorO = plat.assets().getImage("images/floor/dooropen.png");
  Image doorC = plat.assets().getImage("images/floor/doorclosed.png");
  Image hullO = plat.assets().getImage("images/floor/hullopen.png");
  Image hullC = plat.assets().getImage("images/floor/hullclosed.png");

  Image selection1 = plat.assets().getImage("images/selection/sel1.png");
  Image selection2 = plat.assets().getImage("images/selection/sel2.png");
  Image selection3 = plat.assets().getImage("images/selection/sel3.png");
  Image selection4 = plat.assets().getImage("images/selection/sel4.png");
  Image selection5 = plat.assets().getImage("images/selection/sel5.png");
  Image selection6 = plat.assets().getImage("images/selection/sel6.png");
  Image selection7 = plat.assets().getImage("images/selection/sel7.png");
  Image selection8 = plat.assets().getImage("images/selection/sel8.png");
  Image selection9 = plat.assets().getImage("images/selection/sel9.png");

  Image flImage1 = plat.assets().getImage("images/floor/floor1.png");
  Image flImage11 = plat.assets().getImage("images/floor/floor11.png");
  Image flImage2 = plat.assets().getImage("images/floor/floor2.png");
  Image flImage3 = plat.assets().getImage("images/floor/floor3.png");
  Image flImage4 = plat.assets().getImage("images/floor/floor4.png");
  Image flImage5 = plat.assets().getImage("images/floor/floor5.png");
  Image flImage6 = plat.assets().getImage("images/floor/floor6.png");
  Image flImage7 = plat.assets().getImage("images/floor/floor7.png");
  Image flImage8 = plat.assets().getImage("images/floor/floor8.png");
  Image flsImage = plat.assets().getImage("images/floor/floors.png");
  Image flActiveImage = plat.assets().getImage("images/floor/floora.png");

  Image marineImage = plat.assets().getImage("images/models/marine.png");
  Image marinebImage = plat.assets().getImage("images/models/marineb.png");
  Image commissionerImage = plat.assets().getImage("images/models/Commissioner.png");
  Image commissionerbImage = plat.assets().getImage("images/models/Commissionerb.png");

  Image hudImage = plat.assets().getImage("images/Hud.png");

  Image face1Image = plat.assets().getImage("images/face/Face11.png");
  Image face1bImage = plat.assets().getImage("images/face/Face1b.png");
  Image face11cImage = plat.assets().getImage("images/face/Face11c.png");
  Image face2Image = plat.assets().getImage("images/face/Face2.png");
  Image face3Image = plat.assets().getImage("images/face/Face3.png");

  Image imageM = plat.assets().getImage( "images/menu.png");
  Image menuWide = plat.assets().getImage( "images/menuWide.png");
  Image menuH = plat.assets().getImage( "images/menuH.png");


  Sound mainOST = plat.assets().getMusic("sounds/1");

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
  GroupLayer Objectlayer = new GroupLayer();


  ShipFloor sFloor = new ShipFloor("Стальной пол", "Обычный, ничем не примечательный кусок стали", 100, "sF");
  ShipFloor sWallF = new ShipFloor("Стальная стена", "Стандартная стальная обшивка", 100, "sWF");
  ShipFloor sWalFb = new ShipFloor("Стальная стена", "Стандартная стальная обшивка", 100, "sWFb");
  ShipFloor sWallU = new ShipFloor("Стальная стена", "Стандартная стальная обшивка", 100, "sWFU");
  ShipFloor sWallR = new ShipFloor("Стальная стена", "Стандартная стальная обшивка", 100, "sWFR");
  ShipFloor sPassU = new ShipFloor("Дорога", "Стандартная стальная обшивка", 100, "sFUP");
  ShipFloor sPassA = new ShipFloor("Дорога", "Стандартная стальная обшивка", 100, "sFAll");
  ShipFloor sPassG = new ShipFloor("Дорога", "Стандартная стальная обшивка", 100, "sFGOR");
  ShipFloor sPassT = new ShipFloor("Дорога", "Стандартная стальная обшивка", 100, "sFT");
  ShipFloor sPasTb = new ShipFloor("Дорога", "Стандартная стальная обшивка", 100, "sFTb");
  ShipFloor emptyF  = new ShipFloor("Межзвёздная пустота", "Обычный вакуум", 0, "emptyF");
  ShipFloor emptyb  = new ShipFloor("Межзвёздная пустота", "Обычный вакуум", 0, "emptyb");

  ShipFloor[][] startShipForm = new ShipFloor[][]{
    {emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, sWalFb, emptyb, emptyb, sWalFb, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {emptyF, emptyF, emptyF, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sFloor, sFloor, sWalFb, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {emptyF, emptyF, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sWalFb, sWalFb, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {sWalFb, sWalFb, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb},
    {emptyb, sFloor, sPassG, sFloor, sFloor, sWalFb, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sFloor, sWalFb},
    {sWalFb, sFloor, sWalFb, sFloor, sFloor, sWalFb, sWalFb, sFloor, sFloor, sWalFb, sWalFb, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sFloor, sFloor, sWalFb, sFloor, sWalFb},
    {emptyb, sFloor, sPassG, sFloor, sFloor, sWalFb, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sPassG, sFloor, sFloor, sPassG, sFloor, sWalFb},
    {sWalFb, sWalFb, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb},
    {emptyF, emptyF, sWalFb, sFloor, sFloor, sPassG, sFloor, sFloor, sWalFb, sWalFb, sWalFb, sPassU, sWalFb, sWalFb, sWalFb, sWalFb, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {emptyF, emptyF, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sFloor, sFloor, sWalFb, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, sWalFb, emptyb, emptyb, sWalFb, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF},
    {emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF, emptyF}
  };

  SpaceShip startShip = new SpaceShip("Победоносный", startShipForm);

  public final void redraw(){
    rootLayer.disposeAll(); //Чистим все слои, принадлежащие корневому слою.
    Floorlayer = new GroupLayer(); //Создаем групповой слой корабля
    Squadlayer = new GroupLayer(); //Создаем групповой слой отряда

    // ----------Background
    // Создаем задник
    ImageLayer bgLayer = new ImageLayer(bgImage);
    bgLayer.setSize(size);
    rootLayer.add(bgLayer); //Добавляем задник корневому слою


    // ----------Starship
    rootLayer.add(Floorlayer);
    new SpaceShipView(Floorlayer,startShip);

    // ----------Objects
    Objectlayer = new GroupLayer();
    rootLayer.add(Objectlayer);
    for (int i=0; i<objectLimit; i++){
      new ObjectView(Objectlayer, objectArr[i]);
    }


    // ----------Selection
    if(selectedUnit!=-1) {
      GroupLayer SelectionLayer = new GroupLayer(); //Создаем групповой слой выделения
      rootLayer.add(SelectionLayer);
      new SelectionView(SelectionLayer);
    };

    // ----------Squad
    rootLayer.add(Squadlayer);    //Добавляем слой отряда корневому слою
    for(int i=0; i<squadLimit; i++) { // Заполняем групповой слой отряда фигурками отряда
        new SquadView(Squadlayer, squad[i], i); //Рисуем члена отряда
    }

    // ----------HUD
    if(showHUD) {
      final GroupLayer Hudlayer = new GroupLayer();
      final GroupLayer UnitInfoLayer = new GroupLayer();
      rootLayer.add(Hudlayer);
      rootLayer.add(UnitInfoLayer);
      new HUD(Hudlayer,size.width(), size.height());
      if(selectedUnit==-1){
      } else {
        new UnitInfo(UnitInfoLayer, 0, size.height()-256, squad[selectedUnit]);
      }
    };
  };

  public class Object {
    public String name, description, type;
    public int hp, x, y;
    public boolean passability;
    Object (String n, String d, int h, int xx, int yy, String t, boolean p){
      name = n;
      description = d;
      hp = h;
      type = t;
      x = xx;
      y = yy;
      passability = p;
    }
  }

  public class ShipFloor {
    public String name, description, type;
    public int hp;

    ShipFloor (String n, String d, int h, String t){
      name = n;
      description = d;
      hp = h;
      type = t;
    }
  }

  public class SpaceShip {
    public String name;
    public ShipFloor[][] floorArray;

    SpaceShip (String n, ShipFloor[][] fArr){
      name = n;
      floorArray = fArr;
    }
  }

  public class SelectionView {
    public SelectionView(final GroupLayer SelectionLayer){
      Image image = selection5;
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 1)) {image = selection1;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 2)) {image = selection2;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 3)) {image = selection3;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 4)) {image = selection4;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 5)) {image = selection5;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 6)) {image = selection6;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 7)) {image = selection7;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 8)) {image = selection8;};
      if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 9)) {image = selection9;};
      final ImageLayer layer = new ImageLayer(image);
      layer.setOrigin(ImageLayer.Origin.UL);
      SelectionLayer.addAt(layer, shipPositionX+squad[selectedUnit].posx*floorw, shipPositionY+squad[selectedUnit].posy*floorh);
    }
  }

  public class SpaceShipView {
    public SpaceShipView(final GroupLayer Floorlayer, SpaceShip s){
      for (int i=0; i<s.floorArray.length;i++){
        for(int j=0; j<s.floorArray[i].length;j++){
          Image image = empty;
          switch (s.floorArray[i][j].type) {
            case "sF":{image = flImage1; break;}
            case "emptyF":{image = empty; break;}
            case "sWF":{image = wallF; break;}
            case "sWFb":{image = walFb; break;}
            case "sWFU":{image = wallU; break;}
            case "sWFR":{image = wallR; break;}
            case "sFUP":{image = passUp; break;}
            case "sFAll":{image = passAll; break;}
            case "sFGOR":{image = passGor; break;}
            case "sFT":{image = passT; break;}
            case "sFTb":{image = passTb; break;}
            default: break;
          }
          final ImageLayer layer = new ImageLayer(image);
          layer.setOrigin(ImageLayer.Origin.UL);
          Floorlayer.addAt(layer, shipPositionX+floorw*j, shipPositionY+floorh*i);
        }
      }
    }
  }

  public class ObjectView {
    public ObjectView(final GroupLayer Objectlayer, Object o){
          Image image = empty;
          switch (o.type) {
            case "hull":{image = newwall; break;}
            case "doorclosed":{image = doorC; break;}
            case "dooropen":{image = doorO; break;}
            case "hullC":{image = hullC; break;}
            case "hullO":{image = hullO; break;}
            default: break;
          }
          final ImageLayer layer = new ImageLayer(image);
          layer.setOrigin(ImageLayer.Origin.UL);
          Objectlayer.addAt(layer, shipPositionX+floorw*o.x, shipPositionY+floorh*o.y);
        }
      }


  public class Unit {//Класс юнита
    public String name, age, role;
    public int hp, energy, mind, morale, hunger, thirst, fireres, electres, bleedres, stunres;
    public int actionPoints;
    public int posx, posy;

    public Unit(String nm, String ag, String rl, int h, int en, int x, int y, int m, int hun) { //Конструктор юнита
      name = nm;
      age  = ag;
      role = rl;
      hp   = h;
      energy = en;
      mind = 100;
      morale = m;
      hunger = hun;
      thirst = 100;
      fireres  = 100;
      electres = 100;
      bleedres = 100;
      stunres  = 100;

      actionPoints = 10;

      posx = x;
      posy = y;
    }

    public Unit(String nm, String ag, String rl, int h, int en, int x, int y) { //Конструктор юнита, перегруз
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



  public class SquadView {

    public SquadView(final GroupLayer Squadlayer, Unit unt, int i) {
      Image mImage = marineImage;
      if(animation) {
        mImage = marineImage;
      } else {
        mImage = marinebImage;
      }
      Image cImage = commissionerImage;
      if(animation) {
        cImage = commissionerImage;
      } else {
        cImage = commissionerbImage;
      }

      if(i==0)
      {
        final ImageLayer layer = new ImageLayer(mImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, shipPositionX+unt.posx*floorw, shipPositionY+unt.posy*floorh);
      }
      if(i==1)
      {
        final ImageLayer layer = new ImageLayer(mImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, shipPositionX+unt.posx*floorw, shipPositionY+unt.posy*floorh);
      }
      if(i==2)
      {
        final ImageLayer layer = new ImageLayer(mImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, shipPositionX+unt.posx*floorw, shipPositionY+unt.posy*floorh);
      }
      if(i==3)
      {
        final ImageLayer layer = new ImageLayer(cImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, shipPositionX+unt.posx*floorw, shipPositionY+unt.posy*floorh);
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

  public class HUD {
    public HUD(final GroupLayer Hudlayer, float sizeX, float sizeY){
      final ImageLayer bottomHUD = new ImageLayer(menuWide);
      final ImageLayer rightHUD = new ImageLayer(menuH);
      Hudlayer.addAt(bottomHUD, 0, sizeY-256);
      Hudlayer.addAt(rightHUD, sizeX-256, 0);
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

    public UnitInfo(final GroupLayer UnitInfoLayer, float x, float y, Unit unt) // Коструктор 0, если выбран юнит
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
        if (animation)
          {imageface = face1Image;}
        else
          {imageface = face1bImage;}
        //if (speakAnimation)
      //    {imageface = face11bImage;}
      //  else
      //    {imageface = face11cImage;}
      } else {
        if (unt.hp >15) {
          imageface = face2Image;
        }
        else {
          imageface = face3Image;
        }
      }

      final ImageLayer layerface = new ImageLayer(imageface);
      layerface.setOrigin(ImageLayer.Origin.UL);
      UnitInfoLayer.addAt(layerface, x+47+17, y+47+17);
      UnitInfoLayer.addAt(layerHP, x+220+100, y+30+15);
      UnitInfoLayer.addAt(layerEnergy, x+220+100, y+30+15+24);
      UnitInfoLayer.addAt(layerMind, x+220+100, y+30+15+15+24+10);
      UnitInfoLayer.addAt(layerMorale, x+220+100, y+30+15+15+24+10+24);
      UnitInfoLayer.addAt(layerHunger, x+220+100, y+30+15+15+24+10+48);
      UnitInfoLayer.addAt(layerThirst, x+220+100, y+30+15+15+24+10+48+24);
      UnitInfoLayer.addAt(layerFireres, x+410+100, y+30+15);
      UnitInfoLayer.addAt(layerElectres, x+410+100, y+30+15+24);
      UnitInfoLayer.addAt(layerBleedres, x+410+100, y+30+15+24+24);
      UnitInfoLayer.addAt(layerStunres, x+410+100, y+30+15+24+24+24);
      UnitInfoLayer.addAt(layerNames, x+410+100, y+30+15+24+24+24+24);

    }
  }


  public final Pointer pointer;

  @Override public void update(Clock clock) {
    super.update(clock);
  };

  public HelloGame(Platform plat) {
    super(plat, 25); // 25 millis per frame = ~40fps

    Unit tychus = new Unit("Тайкус","40","Танк",200,100, 19, 5, 142, 242); //Создаем Тайкуса в c координатами 3 5
    Unit raynor = new Unit("Рейнор","40","ДД",150,150, 20, 7, 422, 144); //Создаем Рейнора с координатами 0 2
    Unit ray = new Unit("Ray","40","Medic", 75 ,75, 20, 6, 11, 42); //Создаем Рейнора с координатами 0 2
    Unit commissioner = new Unit("Сommissioner","40","Сommissioner",125 ,125, 22, 7); //Создаем Рейнора с координатами 0 2


    Object hullblock = new Object("Стена","Обшивка", 100, 5, 5, "hull", false);
    Object dooropen = new Object("Стена","Обшивка", 100, -100, -100, "dooropen", true);
    Object doorclosed = new Object("Стена","Обшивка", 100, -101, -101, "doorclosed", false);

    objectArr[0] = hullblock;

    squad[0] = tychus; // Добавляем Тайкуса в отряд
    squad[1] = raynor; // Добавляем Рейнора в отряд
    squad[2] = ray; // Добавляем Рейнора в отряд
    squad[3] = commissioner; // Добавляем Рейнора в отряд


    objectLimit = 0;
    for(int i = 0; i < startShip.floorArray.length; i++){
      for(int j = 0; j < startShip.floorArray[0].length; j++){
        if (startShip.floorArray[i][j].type == "sWFb"){
          objectArr[objectLimit] = new Object("Стена","Обшивка", 100, j, i, "hull", false);
          objectLimit++;
        }
        if ((startShip.floorArray[i][j].type == "sFUP") || (startShip.floorArray[i][j].type == "sFGOR")){
          objectArr[objectLimit] = new Object("Дверь","Проход", 100, j, i, "doorclosed", false);
          objectLimit++;
        }
        if (startShip.floorArray[i][j].type == "emptyb"){
          objectArr[objectLimit] = new Object("Шлюз","Проход", 100, j, i, "hullC", false);
          objectLimit++;
        }
      }
    }


    update.connect(new Slot<Clock>() {
      public void onEmit (Clock clock) {




        switch (movingWay) {
          case "Left":      {shipPositionX+=22;break;}
          case "Right":     {shipPositionX-=22;break;}
          case "Up":        {shipPositionY+=22;break;}
          case "Down":      {shipPositionY-=22;break;}
          case "LeftUp":    {shipPositionX+=22;shipPositionY+=22;break;}
          case "RightUp":   {shipPositionX-=22;shipPositionY+=22;break;}
          case "LeftDown":  {shipPositionX+=22;shipPositionY-=22;break;}
          case "RightDown": {shipPositionX-=22;shipPositionY-=22;break;}
          default : {break;}
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 1)) {
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 2)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 3)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 4)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 5)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 6)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 7)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 8)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){selectedTimeI++;};
        }

        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 9)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){
            selectedTimeI++;
          };
        }
        if((selectedTime <= (selectedTimeCD*selectedTimeI)) && (selectedTimeI == 9)){
          selectedTime++;
          if (selectedTime == (selectedTimeCD*selectedTimeI)){
            selectedTimeI=1;
            selectedTime = 0;
          };
        }


        if(faceCoolDown >= 0){faceCoolDown--;};
        if(faceCoolDown < 0){
          animation = !animation;
          faceCoolDown = 10;
        }

        if (soundCoolDown2 > 0){soundCoolDown2--;
        speakAnimation=!speakAnimation;};       // уменьшение кд на воспроизведение реплики
        if (soundCoolDown3 > 0){soundCoolDown3--;};       // уменьшение кд на воспроизведение реплики
        if (startdelay<=0){
          startdelay=0;
          redraw();
        } else{
          startdelay--;
        }
      }
    });
      mainOST.setLooping(true);
      mainOST.setVolume(0.06f);
      mainOST.play();



    plat.input().mouseEvents.connect(new Mouse.MotionSlot() {
      public void onEmit (Mouse.MotionEvent event) {
        if((event.x() < 15) && (event.y() < 15))
        {
          movingWay = "LeftUp";
        }else{
          if((event.x() > (size.width()-15) ) && (event.y() < 15 )){
            movingWay = "RightUp";
          }else{
            if((event.x() < 15) && (event.y() > (size.height()-15) )){
              movingWay = "LeftDown";
            }else{
              if((event.x() > (size.width()-15)) && (event.y() > (size.height()-15) )){
                movingWay = "RightDown";
              }else{
                if(event.x() < 15){
                  movingWay = "Left";
                }else{
                  if(event.x() > (size.width()-15)){
                    movingWay = "Right";
                  }else{
                    if(event.y() < 15){
                      movingWay = "Up";
                    }else{
                      if(event.y() > (size.height()-15)){
                        movingWay = "Down";
                      }else{
                        movingWay = "None";
                      }
                    }
                  }
                }
              }
            }
          }
        }

      };
    });

    // Обработчик клавиатуры
    plat.input().keyboardEvents.connect(new Keyboard.KeySlot() {
      public void onEmit (Keyboard.KeyEvent ev) {
        switch (ev.key) {
          case SHIFT: {
           shiftDown = ev.down;
           break;
          }
          case UP: {
           keyUpDown = ev.down;
           if(keyUpDown){
           movingWay = "Up";
           }
           else{movingWay = "none";}
           break;
          }
          case DOWN: {
           keyDownDown = ev.down;
           if(keyDownDown){
           movingWay = "Down";
           }
           else{movingWay = "none";}
           break;
          }
          case LEFT: {
           keyLeftDown = ev.down;
           if(keyLeftDown){
           movingWay = "Left";
           }
           else{movingWay = "none";}
           break;
          }
          case RIGHT: {
           keyRightDown = ev.down;
           if(keyRightDown){
           movingWay = "Right";
           }
           else{movingWay = "none";}
           break;
          }
          case W: {
           wDown = ev.down;
           if(wDown){
             boolean nowall = true;
             for (int i = 0; i < objectLimit; i++){
               if ((objectArr[i].x == squad[selectedUnit].posx) && (objectArr[i].y == (squad[selectedUnit].posy-1))){
                 nowall = objectArr[i].passability;
                 break;
               }
             }
             if (nowall){
               squad[selectedUnit].posy = squad[selectedUnit].posy -1;
               selectedUnity--;
             }
           };
           break;
          }

          case S: {
           sDown = ev.down;
           if(sDown){
             boolean nowall = true;
             for (int i = 0; i < objectLimit; i++){
               if ((objectArr[i].x == squad[selectedUnit].posx) && (objectArr[i].y == (squad[selectedUnit].posy+1))){
                 nowall = objectArr[i].passability;
                 break;
               }
             }
             if (nowall){
               squad[selectedUnit].posy = squad[selectedUnit].posy +1;
               selectedUnity--;
             }
           };
           break;
          }

          case A: {
           aDown = ev.down;
           if(aDown){
             boolean nowall = true;
             for (int i = 0; i < objectLimit; i++){
               if ((objectArr[i].x == (squad[selectedUnit].posx-1)) && (objectArr[i].y == squad[selectedUnit].posy)){
                 nowall = objectArr[i].passability;
                 break;
               }
             }
             if (nowall){
               squad[selectedUnit].posx = squad[selectedUnit].posx -1;
               selectedUnity--;
             }
           };
           break;
          }

          case D: {
           dDown = ev.down;
           if(dDown){
               boolean nowall = true;
               for (int i = 0; i < objectLimit; i++){
                 if ((objectArr[i].x == (squad[selectedUnit].posx+1)) && (objectArr[i].y == squad[selectedUnit].posy)){
                   nowall = objectArr[i].passability;
                   break;
                 }
               }
               if (nowall){
                 squad[selectedUnit].posx = squad[selectedUnit].posx +1;
                 selectedUnity--;
               }
           };
           break;
          }

          case CONTROL:{
            ctrlDown = ev.down;
            if (ctrlDown){
              for(int i=0; i<objectLimit; i++){
                if ((objectArr[i].x >= squad[selectedUnit].posx-1) && (objectArr[i].x <= squad[selectedUnit].posx+1) && (objectArr[i].y >= squad[selectedUnit].posy-1) && (objectArr[i].y <= squad[selectedUnit].posy+1))
                {
                  switch(objectArr[i].type){
                    case "doorclosed":{
                      objectArr[i].type = "dooropen";
                      objectArr[i].passability = true;
                      landSounds[0].play();
                      break;
                    }
                    case "dooropen":{
                      objectArr[i].type = "doorclosed";
                       objectArr[i].passability = false;
                       landSounds[0].play();
                       break;
                    }
                    case "hullC":{
                      objectArr[i].type = "hullO";
                       objectArr[i].passability = true;
                       landSounds[0].play();
                       break;
                    }
                    case "hullO":{
                      objectArr[i].type = "hullC";
                       objectArr[i].passability = false;
                       landSounds[0].play();
                       break;
                    }
                  }
                }

              }
            };
            break;
          }
          case TAB:{
            tabDown = ev.down;
            if (!tabDown){
              showHUD = !showHUD;
            }
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

          selectedUnit = -1;
          for(int i=0; i<squadLimit;i++){
            if ((event.x() >= shipPositionX+squad[i].posx*floorw) && (event.x() <= shipPositionX+(squad[i].posx+1)*floorw) && (event.y() >= shipPositionY+squad[i].posy*floorh) && (event.y() <= shipPositionY+(squad[i].posy+1)*floorh)){
              selectedUnit=i;
              selectedUnitx=shipPositionX+squad[i].posx*floorw;
              selectedUnity=shipPositionY+squad[i].posy*floorh;


              if (i == 3){
                    if (soundCoolDown3 <= 0){
                      commissionerSounds[soundCounter3].setVolume(0.05f);
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
          if ((selectedUnit== -1) &&
              (event.x() <=shipPositionX+startShip.floorArray.length*floorw ) &&
              (event.y() <=shipPositionY+startShip.floorArray[0].length*floorh) &&
              (event.x() >= shipPositionX) &&
              (event.y() >= shipPositionY)
              ){
            landSounds[0].play();
          }
        };
      }
    });
  }
}
