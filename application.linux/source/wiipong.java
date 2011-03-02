import processing.core.*; 
import processing.xml.*; 

import lll.wrj4P5.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class wiipong extends PApplet {



Wrj4P5 wii;

int c = color(0,0,0); //ball color
int width_=600; 
int height_=500;
float x = width_; //ball position
float y = width_; //ball position
float speed = 5;

boolean back=false;
float yuser=0; 
int imp=0;
int lives=3;
PFont f;
float Y,oldY=0;
boolean conn=false;

float r=random(255/20); //background
float g=random(255/20);
float b=random(255/20);


public void setup() {
  size(width_,height_);
  f=loadFont("Purisa-48.vlw");

  newpos();
  
  wii=new Wrj4P5(this);
  wii.connect();

}

public void draw() {
  if(conn==true || (wii.rimokon.isConnected())){
    conn=true;
    backg();
  
  
    if(lives<=0){
      textFont(f,40);
      text("Game Over",width/2-100,height/2);
      return;
    }
    
    move();
    
    fill(255);
    textFont(f,26);
    text("Lives: "+lives,width-110,height-10);
    textFont(f,40);
    text(imp,width-(25*ln(imp)),30);
    Y=(float)((wii.rimokon.senced.x+1)*(height/3+50));

    Y=stab(Y);

    display();
  }else{
    fill(0);
    textFont(f,16);
    text("Trying to find a Wiimote",width/2-100,height/2);
  }
}

public int ln(int n){
  int i=0;
  while (n>0){
    n=n/10;
    i++;
    println(n);
  }
  return i;
}

public float stab(float Y){
  if (abs(Y-oldY)>2.5f){
        oldY=Y;
        return Y;
  }
  return oldY;
}

public void move() {
  if(!back){
    x = x - speed;
    if (x <0) {
      println("-1");
      lives--;
      newpos();
    }
  }else{
    x = x + speed;
    if(x>=width){
      back=false;
      newpos();
    }
  }
}

public void newpos(){
    x = width;
    y=random(height-30);
    c=color((int)random(255),(int)random(255),(int)random(255));
}

public void display() {
  fill(c);
  stroke(c);
  rect(x,y,30,30);
  fill(20);
  noStroke();
  rect(10, Y,40,80);
  if(impact()){
    back=true;
    imp++;
    println(imp);
    if(imp%5==0)
      speed++;
  }
}

public boolean impact(){
  if((x<=50 && x>(50-speed)) && ((y<Y+80) && (y+40>Y)))
    return true;
  return false;
}

public void backg(){
  for(int i=0;i<height/20;i++){
    fill(color((i+1)*r,(i+1)*g,(i+1)*b));
    noStroke();
    rect(0,20*i,width,20);
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#DFDFDF", "wiipong" });
  }
}
