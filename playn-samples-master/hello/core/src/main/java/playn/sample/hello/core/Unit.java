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

public class Unit {//Класс юнита
  public String name, age, role;
  public int hp, energy, mind, morale, hunger, thirst, fireres, electres, bleedres, stunres;
  public int actionPoints, initiative;
  public int posx, posy;



  public Unit(String nm, String ag, String rl, int h, int en, int x, int y, int m, int hun, int init) { //Конструктор юнита
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

    initiative = init;


    actionPoints = 10;

    posx = x;
    posy = y;
  }

  public Unit(String nm, String ag, String rl, int h, int en, int x, int y, int init) { //Конструктор юнита, перегруз
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

    initiative = init;

    actionPoints = 10;

    posx = x;
    posy = y;
  }

  public Unit(String nm)
  {
    name = nm;
  }

}
