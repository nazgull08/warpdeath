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

public class OurMouse {

  static public void movingClickMouse(Object[] objectArr, Mouse.ButtonEvent event ,Unit selectedUnit, Sound[] commissionerSounds, World w) {
    //int obposx = (int) ((event.x() - w.shipPositionX) / w.floorw);
    //int obposy = (int) ((event.y() - w.shipPositionY) / w.floorh);
    Position dataPos = fromIsometric((int)event.x(),(int)event.y(),w);
    boolean nowall = false;
    boolean noobj = true;
    for (int i = 0; i < w.objectLimit; i++){
    //  if ((event.x() >= w.shipPositionX+objectArr[i].x*w.floorw) && (event.x() <= w.shipPositionX+(objectArr[i].x+1)*w.floorw) && (event.y() >= w.shipPositionY+objectArr[i].y*w.floorh) && (event.y() <= w.shipPositionY+(objectArr[i].y+1)*w.floorh))
      if (objectArr[i].x==dataPos.x&&objectArr[i].y==dataPos.y)
      {
        nowall = objectArr[i].passability;
        noobj=false;

        break;
      }

    }
    if(noobj || (nowall && !noobj)){
      selectedUnit.posx = dataPos.x;
      selectedUnit.posy = dataPos.y;
      commissionerSounds[2].setVolume(0.1f);
      commissionerSounds[2].play();
    }
  }

  static public void cursorState(Mouse.MotionEvent event, IDimension size, World w)
  {
    w.cursor = new Position((int)event.x(), (int)event.y());
  }

  static public Position fromIsometric(int x, int y, World w){
    int posx = x-w.shipPositionX+58;
    int posy = y-w.shipPositionY+58;
    int dataX = (int) (posx/(w.isofloorw/2) + posy/(w.isofloorh/2)) /2;
    int dataY = (int) (posy/(w.isofloorh/2) -(posx/(w.isofloorw/2))) /2;
    return (new Position(dataX, dataY));
  }

  static public String movingWayMouse(Mouse.MotionEvent event, IDimension size){
    String way = "None";
    if((event.x() < 15) && (event.y() < 15))
    {
      way = "LeftUp";
    }else{
      if((event.x() > (size.width()-15) ) && (event.y() < 15 )){
        way = "RightUp";
      }else{
        if((event.x() < 15) && (event.y() > (size.height()-15) )){
          way = "LeftDown";
        }else{
          if((event.x() > (size.width()-15)) && (event.y() > (size.height()-15) )){
            way = "RightDown";
          }else{
            if(event.x() < 15){
              way = "Left";
            }else{
              if(event.x() > (size.width()-15)){
                way = "Right";
              }else{
                if(event.y() < 15){
                  way = "Up";
                }else{
                  if(event.y() > (size.height()-15)){
                    way = "Down";
                  }else{
                    way = "None";
                  }
                }
              }
            }
          }
        }
      }
    }
    return way;
  }
}
