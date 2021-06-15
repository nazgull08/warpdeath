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
import playn.core.Mouse.ButtonEvent.Id;
import playn.core.Mouse.MotionEvent;

import playn.core.Tile;
import react.RFuture;
import react.Slot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import react.UnitSlot;
import static java.lang.Math.max;
import static java.lang.Math.floor;
import pythagoras.f.IDimension;


public class HelloGame extends SceneGame {
  World w = new World();

  Unit[] squad = new Unit[10]; //Создаем массив юнитов. Наш отряд. Максимальный объем - 10 юнитов.
  Object[] objectArr = new Object[24*12];
  int squadLimit = 4; // Текущий предел отряда
  Unit turnfirst[] = new Unit[squadLimit]; //Создаем массив юнитов. Наш отряд. Максимальный объем - 10 юнитов.

  IDimension size = plat.graphics().screenSize();


  int selectedUnit = -1; // если не выбран никакой, то -1. Номер выбранного на данный момент юнита(все в мас)

  int masx = 10;
  int masy = 10;
  int ii = 0;
  int jj = 0;
//  int w.floorw = 128;
//  int w.floorh = 128;
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
  int maxin = -10;
  int top = 0;

  float cameraX = 0;
  float cameraY = 0;

  int actionPointsDef = 4;

  int shipPositionX = 0;
  int shipPositionY = 0;

  int startdelay = 20;

  boolean showHUD = true;

  boolean showWay = false;

  boolean animation = true;
  boolean speakAnimation = true;

  boolean keyUpDown, keyDownDown, keyLeftDown, keyRightDown, eDown, ctrlDown, tabDown, wDown, qDown, sDown, aDown, dDown, tDown;

  Graphics gfx = plat.graphics();

  private boolean loaded;

  String movingWay = "none";

        // "загрузка" изображений

  Image bgImage = plat.assets().getImage("images/twin.png");


  Image isoTest = plat.assets().getImage("images/isoTest1.png");
  Image isoWall = plat.assets().getImage("images/isoWall2.png");
  Image isoWall1 = plat.assets().getImage("images/isoWall3.png");

  Image isoDoorO = plat.assets().getImage("images/isoDoorO.png");
  Image isoDoorC = plat.assets().getImage("images/isoDoorC.png");

  Image isoEmpty = plat.assets().getImage("images/isoEmpty.png");

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

  Image wayImage = plat.assets().getImage("images/floor/way.png");

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

  Image atackImage = plat.assets().getImage("images/button/buttonAtack.png");
  Image atack1Image = plat.assets().getImage("images/button/buttonAtack1.png");
  Image grenadeImage = plat.assets().getImage("images/button/buttonGrenade.png");
  Image grenade1Image = plat.assets().getImage("images/button/buttonGrenade1.png");

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
    plat.assets().getSound("sounds/landSounds/blip"),
    plat.assets().getSound("sounds/landSounds/nice"),
    plat.assets().getSound("sounds/landSounds/neat"),
  };
  Sound[] fireSounds = new Sound[] {                      // создание и присваивание значений массиву звуков
    plat.assets().getSound("sounds/fire/railgun"),

  };

  GroupLayer Floorlayer = new GroupLayer(); //Создаем групповой слой корабля
  GroupLayer Squadlayer = new GroupLayer(); //Создаем групповой слой отряда
  GroupLayer Objectlayer = new GroupLayer();


    // "заготовка компонетов для корабля"

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

    // сами корабли
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

  ShipFloor[][] boxShipForm = new ShipFloor[][]{
    {sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb},
    {sWalFb, sFloor, sFloor, sFloor, sWalFb, sFloor, sFloor, sFloor, sWalFb},
    {sFloor, sFloor, sFloor, sFloor, sWalFb, sFloor, sFloor, sFloor, sWalFb},
    {sWalFb, sFloor, sFloor, sFloor, sWalFb, sFloor, sFloor, sFloor, sWalFb},
    {sWalFb, sFloor, sFloor, sFloor, sFloor, sFloor, sWalFb, sFloor, sWalFb},
    {sWalFb, sFloor, sFloor, sFloor, sWalFb, sFloor, sFloor, sFloor, sWalFb},
    {sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb, sWalFb},
  };


  SpaceShip startShip = new SpaceShip("Победоносный", startShipForm);
  SpaceShip testship = new SpaceShip("Test", boxShipForm);
  //SpaceShip testship = new SpaceShip("Победоносный", startShipForm);

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
    new SpaceShipView(Floorlayer,testship);

    // ----------Objects
    Objectlayer = new GroupLayer();
    rootLayer.add(Objectlayer);
    for (int i=0; i<w.objectLimit; i++){
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

    if(showWay) {
      final GroupLayer Waylayer = new GroupLayer();
      rootLayer.add(Waylayer);
      new WayView(Waylayer, w.wayPos);
    };


//ISOMETRIC
    final GroupLayer isoLayer = new GroupLayer(); //яф
    rootLayer.add(isoLayer);
//    new IsoFloor(isoLayer);

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
      Position isoPos = toIsometric(squad[selectedUnit].posx,squad[selectedUnit].posy);
      SelectionLayer.addAt(layer, isoPos.x+64, isoPos.y-32);
    }
  }


  // зарисовка корабля
  public class SpaceShipView {
    public SpaceShipView(final GroupLayer Floorlayer, SpaceShip s){
      for (int i=0; i<s.floorArray.length;i++){
        for(int j=0; j<s.floorArray[i].length;j++){
          Image image = empty;
          int height = 0;
          switch (s.floorArray[i][j].type) {
            case "sF":{image = isoTest; break;}
            case "emptyF":{image = isoEmpty; break;}
            case "sWF":{image = wallF; break;}
            case "sWFb":{image = isoWall1; height=w.isoHeight; break;}
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
          Position isoPos = toIsometric(j,i,height);
          Floorlayer.addAt(layer, isoPos.x, isoPos.y);
        }
      }
    }
  }

  public class ObjectView {
    public ObjectView(final GroupLayer Objectlayer, Object o){
          Image image = empty;
          switch (o.type) {
            case "hull":{image = isoWall1; break;}
            case "doorclosed":{image = isoDoorC; break;}
            case "dooropen":{image = isoDoorO; break;}
            case "hullC":{image = hullC; break;}
            case "hullO":{image = hullO; break;}
            default: break;
          }
          final ImageLayer layer = new ImageLayer(image);
          layer.setOrigin(ImageLayer.Origin.UL);
          Position isoPos = toIsometric(o.x,o.y,w.isoHeight);
          Objectlayer.addAt(layer, isoPos.x, isoPos.y);
        }
      }

  public class WayView {
    public WayView(final GroupLayer Waylayer, Position[] way){

      for(int i=0;i<way.length;i++){
          final ImageLayer layer = new ImageLayer(wayImage);
          layer.setOrigin(ImageLayer.Origin.UL);
            Waylayer.addAt(layer, w.shipPositionX+w.floorw*way[i].x, w.shipPositionY+w.floorh*way[i].y);
          }
        }
      }

  public class IsoFloor {
    public IsoFloor(final GroupLayer isoLayer){
      for(int i=0;i<10;i++){
        for(int j=0; j<10; j++){
          if(i!=0&&j!=0){
          final ImageLayer layer = new ImageLayer(isoTest);
          layer.setOrigin(ImageLayer.Origin.UL);
          Position isoPos = toIsometric(i,j);
          isoLayer.addAt(layer, isoPos.x, isoPos.y);
        }else {
          final ImageLayer layer = new ImageLayer(isoDoorO);
          layer.setOrigin(ImageLayer.Origin.UL);
          Position isoPos = toIsometric(i,j,48);
          isoLayer.addAt(layer, isoPos.x, isoPos.y);

        }
        }
      }
    }
  }
/*
      class SortByInit implements Comparator<Unit>
      {
        public int compare(Unit a, Unit b) {
        if ( a.initiative < b.initiative ) return -1;
        else if ( a.initiative == b.initiative ) return 0;
        else return 1;
        }
      }
*/



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
      Position isoPos = toIsometric(unt.posx,unt.posy);
      if(squad[i].name == "PРейнор")
      {
        final ImageLayer layer = new ImageLayer(mImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, isoPos.x+64, isoPos.y-32);
      }
      if(squad[i].name == "TТайкус")
      {
        final ImageLayer layer = new ImageLayer(mImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, isoPos.x+64, isoPos.y-32);
      }
      if(squad[i].name == "Ray")
      {
        final ImageLayer layer = new ImageLayer(mImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, isoPos.x+64, isoPos.y-32);
      }
      if(squad[i].name == "Commissioner")
      {
        final ImageLayer layer = new ImageLayer(cImage);
        layer.setOrigin(ImageLayer.Origin.UL);
        Squadlayer.addAt(layer, isoPos.x+64, isoPos.y-32);
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
      String points = "Action Points: " + String.valueOf(unt.actionPoints);
      String initiative1 = "Initiative: " + String.valueOf(unt.initiative);
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
      TextLayout layoutPoints = gfx.layoutText(points, format); // получаем надпись
      TextLayout layoutInitiative1 = gfx.layoutText(initiative1, format); // получаем надпись
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
      Layer layerPoints = createTextLayer(layoutPoints, 0xFFA9A9A9); // запекаем надпись в картинку
      Layer layerInitiative = createTextLayer(layoutInitiative1, 0xFF6A5ACD); // запекаем надпись в картинку
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
      final ImageLayer layerAtack = new ImageLayer(atackImage);
      final ImageLayer layerAtack1 = new ImageLayer(atack1Image);
      final ImageLayer layerGrenade = new ImageLayer(grenadeImage);
      final ImageLayer layerGrenade1 = new ImageLayer(grenade1Image);
      layerface.setOrigin(ImageLayer.Origin.UL);
      UnitInfoLayer.addAt(layerface, x+47+17, y+47+17);
      UnitInfoLayer.addAt(layerAtack, x+1000, y+30+15);
      UnitInfoLayer.addAt(layerGrenade, x+1100, y+30+15);
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
      UnitInfoLayer.addAt(layerPoints, x+410+100, y+30+15+24+24+24+24+24);
      UnitInfoLayer.addAt(layerInitiative, x+610+100, y+30+15);

    }
  }

  public boolean isPassable(Position p, int objectLimit, Object[] objArr){
      boolean nowall = true;
      for(int i=0;i<objectLimit;i++){
        if ((objArr[i].x==p.x)&&(objArr[i].y==p.y)&&(!objArr[i].passability)){
            nowall=false;
            break;
        }
      }
      return nowall;
    }

  public Position[] getAllPassableBounds(Position p, int oL, Object[] oA){
    int c = 0;
    Position[] bounds = new Position[8];
    Position lu = new Position(p.x-1,p.y-1);
    Position u  = new Position(p.x,p.y-1);
    Position ru = new Position(p.x+1,p.y-1);
    Position l  = new Position(p.x-1,p.y);
    Position r  = new Position(p.x+1,p.y);
    Position ld = new Position(p.x-1,p.y+1);
    Position d  = new Position(p.x,p.y+1);
    Position rd = new Position(p.x+1,p.y+1);
    if(isPassable(lu, oL, oA)){
      bounds[c] = lu;
      c++;
    };
    if(isPassable(u, oL, oA)){
      bounds[c] = u;
      c++;
    };
    if(isPassable(ru, oL, oA)){
      bounds[c] = ru;
      c++;
    };
    if(isPassable(l, oL, oA)){
      bounds[c] = l;
      c++;
    };
    if(isPassable(r, oL, oA)){
      bounds[c] = r;
      c++;
    };
    if(isPassable(ld, oL, oA)){
      bounds[c] = ld;
      c++;
    };
    if(isPassable(d, oL, oA)){
      bounds[c] = d;
      c++;
    };
    if(isPassable(rd, oL, oA)){
      bounds[c] = rd;
      c++;
    };

    Position[] b = new Position[c];
    for(int i = 0; i<c;i++){
      b[i] = bounds[i];
    }
    return b;
  }

  public final Pointer pointer;


  @Override public void update(Clock clock) {
    super.update(clock);
  };

  public int cross_product(int x1, int y1, int x2, int y2, int x,  int y)
  {
      return (x - x2) * (y2 - y1) - (y - y2) * (x2 - x1);
  }

  public boolean point_in_triangle(int x1, int y1, int x2, int y2, int x3, int y3, int x,  int y)
  {
      boolean cp1 = cross_product(x1, y1, x2, y2, x, y) < 0;
      boolean cp2 = cross_product(x2, y2, x3, y3, x, y) < 0;
      boolean cp3 = cross_product(x3, y3, x1, y1, x, y) < 0;
      return cp1 == cp2 && cp2 == cp3 && cp3 == cp1;
  }

  public Position toIsometric(int x, int y){
    //int y = -y1;

    //int x = -x1;
  int isoX = w.shipPositionX+x*(w.isofloorw/2)-y*(w.isofloorw/2);
  int isoY = w.shipPositionY+y*(w.isofloorh/2)+x*(w.isofloorh/2);
  //  int isoX = 0;
  //  int isoY = 0;
    return (new Position(isoX,isoY));
  }

  public Position toIsometric(int x, int y, int h){
    //int y = -y1;
    //int x = -x1;
    int isoX = w.shipPositionX+x*(w.isofloorw/2)-y*(w.isofloorw/2);
    int isoY = w.shipPositionY+y*(w.isofloorh/2)+x*(w.isofloorh/2) - h;
    return (new Position(isoX,isoY));
  }

  public Position fromIsometric(int x, int y){
    int posx = x-w.shipPositionX;
    int posy = y-w.shipPositionY;
    int dataX = (int) (posx/(w.isofloorw/2) + posy/(w.isofloorh/2)-1) /2;
    int dataY = (int) (posy/(w.isofloorh/2) -(posx/(w.isofloorw/2))) /2;
    return (new Position(dataX, dataY));
  }

  public HelloGame(Platform plat) {
    super(plat, 25); // 25 millis per frame = ~40fps

    Unit tychus = new Unit("TТайкус","40","Танк",200,100, 6, 1, 142, 242, 20); //Создаем Тайкуса в c координатами 3 5
    Unit raynor = new Unit("PРейнор","40","ДД",150,150, 1, 1, 422, 144, 14); //Создаем Рейнора с координатами 0 2
    Unit ray = new Unit("Ray","40","Medic", 75 ,75, 7, 5, 11, 42, 7); //Создаем Рейнора с координатами 0 2
    Unit commissioner = new Unit("Commissioner","40","Commissioner",125 ,125, 6, 5, 10); //Создаем Рейнора с координатами 0 2
    Unit hollow = new Unit("Hollow"); //Создаем Рейнора с координатами 0 2

    squad[0] = tychus; // Добавляем Тайкуса в отряд
    squad[1] = raynor; // Добавляем Рейнора в отряд
    squad[2] = ray; // Добавляем Рейнора в отряд
    squad[3] = commissioner; // Добавляем Рейнора в отряд
    squad[4] = hollow; // Добавляем Рейнора в отряд

/*
    for(int i=0; i<squadLimit;i++){
      System.out.printf("Name: %s, init: %d \n", squad[i].name, squad[i].initiative);
    }
    Arrays.sort(squad, new SortByInit());
    for(int i=0; i<squadLimit;i++){
      System.out.printf("Name: %s, init: %d \n", squad[i].name, squad[i].initiative);
    }
*/


    int maxincount = 0;
    int bestpos = 0;
    System.out.printf("LOX1 \n");
    System.out.printf("TOP %d\n",top);

    while(maxincount <= (squadLimit-1))
    {
      System.out.printf("LOX2 \n");
      System.out.printf("!                   !\n");
      System.out.printf("-------------------\n");
      for(int i = maxincount; i <= (squadLimit); i++)
        {

          if(maxin <= squad[i].initiative)
          {
            maxin = squad[i].initiative;
            bestpos = i;
            System.out.printf("LOX \n");
            System.out.printf("bestpos = %d \n",bestpos);
            System.out.printf("bestpos = %d \n",i);
            System.out.printf("-------------------\n");
          }
        }
      maxin = -10;
      squad[4] = squad[maxincount];
      squad[maxincount] = squad[bestpos];
      squad[bestpos] = squad[4];
      maxincount++;

    }
    for(int i=0; i < squadLimit;i++)
    {
      System.out.printf("Name: %s \n", squad[i].name);
      System.out.printf("Name: %d \n", i);
    }

    Object hullblock = new Object("Стена","Обшивка", 100, 5, 5, "hull", false);
    Object dooropen = new Object("Стена","Обшивка", 100, -100, -100, "dooropen", true);
    Object doorclosed = new Object("Стена","Обшивка", 100, -101, -101, "doorclosed", false);

    objectArr[0] = hullblock;



    w.objectLimit = 0;
    for(int i = 0; i < testship.floorArray.length; i++){
      for(int j = 0; j < testship.floorArray[0].length; j++){
        if (testship.floorArray[i][j].type == "sWFb"){
          objectArr[w.objectLimit] = new Object("Стена","Обшивка", 100, j, i, "hull", false);
          w.objectLimit++;
        }
        if ((testship.floorArray[i][j].type == "sFUP") || (testship.floorArray[i][j].type == "sFGOR")){
          objectArr[w.objectLimit] = new Object("Дверь","Проход", 100, j, i, "doorclosed", false);
          w.objectLimit++;
        }
        if (testship.floorArray[i][j].type == "emptyb"){
          objectArr[w.objectLimit] = new Object("Шлюз","Проход", 100, j, i, "hullC", false);
          w.objectLimit++;
        }
      }
    }


    update.connect(new Slot<Clock>() {
      public void onEmit (Clock clock) {




        switch (movingWay) {
          case "Left":      {w.shipPositionX+=22;break;}
          case "Right":     {w.shipPositionX-=22;break;}
          case "Up":        {w.shipPositionY+=22;break;}
          case "Down":      {w.shipPositionY-=22;break;}
          case "LeftUp":    {w.shipPositionX+=22;w.shipPositionY+=22;break;}
          case "RightUp":   {w.shipPositionX-=22;w.shipPositionY+=22;break;}
          case "LeftDown":  {w.shipPositionX+=22;w.shipPositionY-=22;break;}
          case "RightDown": {w.shipPositionX-=22;w.shipPositionY-=22;break;}
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
      mainOST.setVolume(0.01f);
      mainOST.play();


      plat.input().mouseEvents.connect(new Mouse.ButtonSlot() {
        public void onEmit (Mouse.ButtonEvent event)
        {
          if(event.down)
          {
            if(selectedUnit!=(-1))
            {
              if(event.button == Mouse.ButtonEvent.Id.RIGHT)
              {
                OurMouse.movingClickMouseHud(objectArr,event ,squad[selectedUnit],landSounds, w);

              }
            }
          }
        };
      });
//------------------------------------------------------------- CLICK!!!!----
    plat.input().mouseEvents.connect(new Mouse.ButtonSlot() {
      public void onEmit (Mouse.ButtonEvent event)
      {
        if(event.down)
        {
          if(selectedUnit!=(-1))
          {
            if(event.button == Mouse.ButtonEvent.Id.RIGHT)
            {
              OurMouse.movingClickMouse(objectArr,event ,squad[selectedUnit],commissionerSounds, w);
              if(showWay){showWay=!showWay;}
            }
          }
        }
      };
    });
//-------------------------------------------------------------

    plat.input().mouseEvents.connect(new Mouse.MotionSlot() {
      public void onEmit (Mouse.MotionEvent event) {
        movingWay = OurMouse.movingWayMouse(event, size);
        OurMouse.cursorState(event, size, w);
      };
    });


    // Обработчик клавиатуры
    plat.input().keyboardEvents.connect(new Keyboard.KeySlot() {
      public void onEmit (Keyboard.KeyEvent ev) {
        switch (ev.key) {
          case E: {
           eDown = ev.down;
           if(selectedUnit!=-1 && eDown){
             int a = squad[selectedUnit].actionPoints;
             Position startPos = new Position (squad[selectedUnit].posx,squad[selectedUnit].posy);
             Position[] boundsList = getAllPassableBounds(startPos, w.objectLimit, objectArr);
             w.wayPos = boundsList;
             showWay=!showWay;


           };
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
          /*
          case SPACE: {
           if(top == squadLimit)
           {
             for(int i = 0; i < squadLimit; i++)
             {
               squad[0].actionPoints = actionPointsDef;
             }
             top = 0;
           }
           break;

          }
          */
          case SPACE:
          {
           squad[0].actionPoints = actionPointsDef;
           break;
          }

          case Q:
          {
           qDown = ev.down;
           boolean nowall = true;
           if(qDown)
           {
              for(int i = 0; i < squadLimit; i++)
              {
                if(((squad[selectedUnit].posx == squad[i].posx) && (selectedUnit != i))) //|| ((squad[selectedUnit].posy == squad[i].posy) && (selectedUnit != i)))
                {
                int k = squad[selectedUnit].posx;
                System.out.printf("1\n");
                System.out.printf("k %d\n", k);

                  for(k = squad[selectedUnit].posy; k < (squad[i].posy+1); k++)
                    {
                      System.out.printf("k %d\n", k);

                      for (int j = 0; j < w.objectLimit; j++)
                      {
                        if ((objectArr[j].x == squad[selectedUnit].posx) && (objectArr[j].y >= squad[selectedUnit].posy) && (objectArr[j].y <= squad[i].posy) )
                        {
                          System.out.printf("3\n");

                          nowall = objectArr[j].passability;
                          if(!nowall)
                          {
                            System.out.printf("2 %d\n",k);
                          }
                        }
                      }
                    }
                  if(nowall)
                  {
                    fireSounds[0].setVolume(0.2f);
                    fireSounds[0].play();
                    squad[i].hp = squad[i].hp - 10;
                    System.out.printf("squad[i].h: %d \n", squad[i].hp);
                  }
                  break;
                }
              }
           }
         }

          case T:
          {
           tDown = ev.down;
           if(tDown)
           {
             int cursx = w.cursor.x + w.shipPositionX;
             int cursy = w.cursor.y + w.shipPositionY;
             for (int i=0; i<testship.floorArray.length;i++){
               for(int j=0; j<testship.floorArray[i].length;j++){
                 Position isoPos = toIsometric(j,i);
                 if( (isoPos.x <= cursx) && (isoPos.y >= cursy) && ((isoPos.x + w.isofloorw) >= cursx) && ((isoPos.y + w.isofloorh) >= cursy) )
                 {
                 System.out.printf(".!.\n");
                 System.out.printf("isoPos= %d %d\n",isoPos.x, isoPos.y);
                 System.out.printf("curs= %d %d\n",cursx, cursy);
                 boolean one   = false;
                 boolean two   = false;
                 boolean three = false;
                 boolean four  = false;
                 if(point_in_triangle(isoPos.x, isoPos.y + w.isofloorh/2, isoPos.x + w.isofloorw/2, isoPos.y, isoPos.x + w.isofloorw/2, isoPos.y + w.isofloorh/2, cursx, cursy))
                 {
                   one = true;
                 }
                 if(point_in_triangle(isoPos.x + w.isofloorw/2, isoPos.y, isoPos.x + w.isofloorw, isoPos.y + w.isofloorh/2, isoPos.x + w.isofloorw/2, isoPos.y + w.isofloorh/2, cursx, cursy))
                 {
                   two = true;
                 }
                 if(point_in_triangle(isoPos.x, isoPos.y + w.isofloorh/2, isoPos.x+ w.isofloorw/2, isoPos.y + w.isofloorh, isoPos.x + w.isofloorw/2, isoPos.y + w.isofloorh/2, cursx, cursy))
                 {
                   three = true;
                 }
                 if(point_in_triangle(isoPos.x+ w.isofloorw/2, isoPos.y + w.isofloorh, isoPos.x + w.isofloorw, isoPos.y + w.isofloorh/2, isoPos.x + w.isofloorw/2, isoPos.y + w.isofloorh/2, cursx, cursy))
                 {
                   four = true;
                 }


                   if(one || two || three || four)
                   {
                     w.selectedTile = new Position(j,i);
                     System.out.printf("Selected tile %d %d\n", w.selectedTile.x, w.selectedTile.y);
                     System.out.printf("\n");
                     System.out.printf("=============================\n");
                   }

                 }


               }
             }

           }
           break;
          }

          case K1:
          {
           selectedUnit = 0;
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2); // ценрализация камеры
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }
          case K2:
          {
           selectedUnit = 1;
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }
          case K3:
          {
           selectedUnit = 2;
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }
          case K4:
          {
           selectedUnit = 3;
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }


          case W: {
           wDown = ev.down;
           if(wDown){
             boolean nowall = true;
             for (int i = 0; i < w.objectLimit; i++){
               if ((objectArr[i].x == squad[selectedUnit].posx) && (objectArr[i].y == (squad[selectedUnit].posy-1))){
                 nowall = objectArr[i].passability;
                 break;
               }
             }
             if (nowall){
               if((squad[selectedUnit].actionPoints > 0) && (squad[selectedUnit].initiative == squad[top].initiative))
               {
                 squad[selectedUnit].posy = squad[selectedUnit].posy -1;
                 squad[selectedUnit].actionPoints--;
               }
               else
               {
                 landSounds[1].setVolume(1.1f);
                 landSounds[1].play();
                 if(selectedUnit == top)
                 {
                   top++;
                   System.out.printf("TOP %d\n",top);
                 }
               }
             }
           };
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }

          case S: {
           sDown = ev.down;
           if(sDown){
             boolean nowall = true;
             for (int i = 0; i < w.objectLimit; i++){
               if ((objectArr[i].x == squad[selectedUnit].posx) && (objectArr[i].y == (squad[selectedUnit].posy+1))){
                 nowall = objectArr[i].passability;
                 break;
               }
             }
             if (nowall){
               if((squad[selectedUnit].actionPoints > 0) && (squad[selectedUnit].initiative == squad[top].initiative))
               {
               squad[selectedUnit].posy = squad[selectedUnit].posy +1;
               squad[selectedUnit].actionPoints--;
               }
               else
               {
                 landSounds[1].setVolume(1.1f);
                 landSounds[1].play();
                 if(selectedUnit == top)
                 {
                   top++;
                   System.out.printf("TOP %d\n",top);
                 }
               }
             }
           };
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }

          case A: {
           aDown = ev.down;
           if(aDown){
             boolean nowall = true;
             for (int i = 0; i < w.objectLimit; i++){
               if ((objectArr[i].x == (squad[selectedUnit].posx-1)) && (objectArr[i].y == squad[selectedUnit].posy)){
                 nowall = objectArr[i].passability;
                 break;
               }
             }
             if (nowall){
               if((squad[selectedUnit].actionPoints > 0) && (squad[selectedUnit].initiative == squad[top].initiative))
               {
               squad[selectedUnit].posx = squad[selectedUnit].posx -1;
               squad[selectedUnit].actionPoints--;
               }
               else
               {
                 landSounds[1].setVolume(1.1f);
                 landSounds[1].play();
                 if(selectedUnit == top)
                 {
                   top++;
                   System.out.printf("TOP %d\n",top);
                 }
               }
             }
           };
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }

          case D: {
           dDown = ev.down;
           if(dDown){
               boolean nowall = true;
               for (int i = 0; i < w.objectLimit; i++){
                 if ((objectArr[i].x == (squad[selectedUnit].posx+1)) && (objectArr[i].y == squad[selectedUnit].posy)){
                   nowall = objectArr[i].passability;
                   break;
                 }
               }
               if (nowall){
                 if((squad[selectedUnit].actionPoints > 0) && (squad[selectedUnit].initiative == squad[top].initiative))
                 {
                 squad[selectedUnit].posx = squad[selectedUnit].posx +1;
                 squad[selectedUnit].actionPoints--;

                 }
                 else
                 {
                   landSounds[1].setVolume(1.1f);
                   landSounds[1].play();

                   if(selectedUnit == top)
                   {
                     top++;
                     System.out.printf("TOP %d\n",top);
                   }
                   /*
                   if((selectedUnit == squadLimit) && (selectedUnit < top))
                   {
                     top = 0;
                   }
                   */
                 }
               }
           };
           w.shipPositionX = -squad[selectedUnit].posx*w.floorw + (int)((size.width() -256)/ 2);
           w.shipPositionY = -squad[selectedUnit].posy*w.floorh + (int)((size.height() -256)/ 2);
           break;
          }

          case CONTROL:{
            ctrlDown = ev.down;
            if (ctrlDown){
              for(int i=0; i<w.objectLimit; i++){
                if ((objectArr[i].x >= squad[selectedUnit].posx-1) && (objectArr[i].x <= squad[selectedUnit].posx+1) && (objectArr[i].y >= squad[selectedUnit].posy-1) && (objectArr[i].y <= squad[selectedUnit].posy+1))
                {
                  if(squad[selectedUnit].actionPoints <= 0){
                    landSounds[1].setVolume(1.1f);
                    landSounds[1].play();
                    break;
                  }
                  else{
                  switch(objectArr[i].type){
                    case "doorclosed":{
                      objectArr[i].type = "dooropen";
                      objectArr[i].passability = true;
                      landSounds[0].play();
                      squad[selectedUnit].actionPoints--;
                      break;
                    }
                    case "dooropen":{
                      objectArr[i].type = "doorclosed";
                       objectArr[i].passability = false;
                       landSounds[0].play();
                       squad[selectedUnit].actionPoints--;
                       break;
                    }
                    case "hullC":{
                      objectArr[i].type = "hullO";
                       objectArr[i].passability = true;
                       landSounds[0].play();
                       squad[selectedUnit].actionPoints--;
                       break;
                    }
                    case "hullO":{
                      objectArr[i].type = "hullC";
                       objectArr[i].passability = false;
                       landSounds[0].play();
                       squad[selectedUnit].actionPoints--;
                       break;
                    }
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
          Position dataPos = fromIsometric((int)event.x(),(int)event.y());
          System.out.printf("Click: %f, %f; Pos: %d, %d\n",event.x(),event.y(),dataPos.x,dataPos.y);
          selectedUnit = -1;
          for(int i=0; i<squadLimit;i++){
    //        if ((event.x() >= w.shipPositionX+squad[i].posx*w.floorw) && (event.x() <= w.shipPositionX+(squad[i].posx+1)*w.floorw) && (event.y() >= w.shipPositionY+squad[i].posy*w.floorh) && (event.y() <= w.shipPositionY+(squad[i].posy+1)*w.floorh)){
            if (squad[i].posx==dataPos.x&&squad[i].posy==dataPos.y){
              selectedUnit=i;


              if (squad[selectedUnit].name=="Commissioner"){
                    if (soundCoolDown3 <= 0){
                      commissionerSounds[soundCounter3].setVolume(0.05f);
                      commissionerSounds[soundCounter3].play();    //воспроизведение звуков
                      soundCounter3++;     //счетчик реплик
                      soundCoolDown3 = 100;   // установка кд реплики
                    }
                    if (soundCounter3 == 5){soundCounter3 = 0;};  // сброс реплик
                  };



              if (squad[selectedUnit].name=="Ray"){
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
              (event.x() <=w.shipPositionX+testship.floorArray.length*w.floorw ) &&
              (event.y() <=w.shipPositionY+testship.floorArray[0].length*w.floorh) &&
              (event.x() >= w.shipPositionX) &&
              (event.y() >= w.shipPositionY)
              ){
            landSounds[0].play();
          }
        };
      }
    });
  }
}
