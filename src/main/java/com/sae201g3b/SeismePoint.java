package com.sae201g3b;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Affiche un point sur la carte */
public class SeismePoint extends MapLayer {

 private final MapPoint mapPoint;
 private final Circle circle;

 public SeismePoint(MapPoint mapPoint, float intensite) {
  this.mapPoint = mapPoint;
  /* Cercle de taille 5 dont la couleur varie selon l'intesité du seisme qu'il représente */
  if(intensite <= 3 ){
   this.circle = new Circle(5, Color.GREEN);
  }else if (intensite > 3 && intensite <= 6){
   this.circle = new Circle(5, Color.YELLOW);
  }else if (intensite > 6 && intensite <= 9){
   this.circle = new Circle(5, Color.ORANGE);
  }else if (intensite > 9){
   this.circle = new Circle(5, Color.RED);
  } else{
   this.circle = new Circle(5, Color.BLUE);
  }
  /* Ajoute le cercle au MapLayer */
  this.getChildren().add(circle);
 }

 /* La fonction est appelée à chaque rafraichissement de la carte */
 @Override
 protected void layoutLayer() {
  /* Conversion du MapPoint vers Point2D */
  Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
  /* Déplace le cercle selon les coordonnées du point */
  circle.setTranslateX(point2d.getX());
  circle.setTranslateY(point2d.getY());
 }
}