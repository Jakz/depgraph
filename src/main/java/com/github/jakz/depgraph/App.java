package com.github.jakz.depgraph;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
  public static void main(String[] args)
  {
    
    try
    {
      SrcPath src = new SrcPath(Paths.get("/Users/jack/Documents/Dev/Junk Jack/src/core"));
      Set<SrcFile> files = src.scan();
      files.forEach(f -> System.out.println(f.toString()));
    } 
    catch (IOException e) 
    {
    
      e.printStackTrace();
    }
  }
}
