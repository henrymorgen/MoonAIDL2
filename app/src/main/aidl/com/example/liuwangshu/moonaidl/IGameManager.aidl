package com.example.liuwangshu.moonaidl;
import com.example.liuwangshu.moonaidl.Game;
interface IGameManager {
  List<Game>getGameList();
  void addGame(in Game game);
}