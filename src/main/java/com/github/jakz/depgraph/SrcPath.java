package com.github.jakz.depgraph;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SrcPath
{
  public static enum Type
  {
    INCLUDE,
    SOURCE
  };
  
  public final Path path;
  public final boolean recursive;
  public final Type type;
  
  public SrcPath(Path path)
  {
    this.path = path;
    this.recursive = true;
    this.type = Type.SOURCE;
  }
  
  @Override
  public int hashCode()
  {
    return path.hashCode();
  }
  
  @Override
  public boolean equals(Object o)
  {
    return o instanceof SrcPath && ((SrcPath)o).path.equals(path);
  }
  
  public Set<SrcFile> scan() throws IOException
  {        
    //TODO: recursive flag
    try (Stream<Path> stream = Files.walk(path))
    {
      return stream.filter(Files::isRegularFile)
          .map(SrcFile::new)
          .filter(SrcFile::isSupported)
          .collect(Collectors.toSet());
    }
  }
}
