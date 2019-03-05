package com.github.jakz.depgraph;

import java.nio.file.Path;
import java.util.Arrays;

import com.pixbits.lib.io.FileUtils;

public class SrcFile implements Comparable<SrcFile>
{
  public static enum Type
  {
    CPP_SOURCE("cpp", "cxx"),
    CPP_HEADER("hpp", "hxx"),
    C_SOURCE("c"),
    C_HEADER("h"),
    
    UNKNOWN("")
    ;
    
    public final String[] extensions;
    
    private Type(String... extensions)
    {
      this.extensions = extensions;
    }
    
    public boolean hasExtension(String ext)
    {
      return Arrays.stream(extensions)
          .anyMatch(e -> e.equals(ext));
    }
  };
  
  public final Type type;
  public final Path path;
  
  public SrcFile(Path path)
  {
    this.path = path;
    
    String extension = FileUtils.pathExtension(path);
    
    this.type = Arrays.stream(Type.values())
      .filter(type -> type.hasExtension(extension))
      .findFirst()
      .orElse(Type.UNKNOWN);
  }
  
  public boolean isSupported()
  { 
    return type != Type.UNKNOWN;
  }

  @Override
  public int compareTo(SrcFile o)
  {
    return path.compareTo(o.path);
  }
  
  @Override
  public String toString()
  {
    return path.toString();
  }
}
