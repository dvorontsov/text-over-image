# Text Over Image

## Service Project
https://github.com/dvorontsov/text-over-image/tree/master/text-over-image-service
Project is built as a service that can be used by other applications.  It can be wrapped into a REST endpoint or an executable.

Service api can be found here - https://github.com/dvorontsov/text-over-image/blob/master/text-over-image-service/src/main/java/denv/graphics/textoverimage/api/TextOverImageService.java

## Executable Project
https://github.com/dvorontsov/text-over-image/tree/master/text-over-image-executable
Project is an executable jar that can be executed from command line with a command line this
```
java -jar jar-file-name input-file.json
```


## Example 
Input file such as 
```
https://github.com/dvorontsov/text-over-image/blob/master/examples/example-1/text-over-image-input.json
```
which contains the following json
```
{
  "width": 800,
  "height": 416,
  "gradient" : {
    "source": {
      "r": 127,
      "g": 178,
      "b": 255,
      "a": 255
    },
    "dest": {
      "r": 255,
      "g": 255,
      "b": 255,
      "a": 255
    }
  },
  "imageLayers": [
    {
      "xCoordinate": 40,
      "yCoordinate": 110,
      "text": "do {",
      "color": {
        "r": 22,
        "g": 63,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 1,
      "fontSize": 35
    },
    {
      "xCoordinate": 100,
      "yCoordinate": 170,
      "text": "iLookAtMyCode();",
      "color": {
        "r": 22,
        "g": 63,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 1,
      "fontSize": 35
    },
    {
      "xCoordinate": 100,
      "yCoordinate": 230,
      "text": "andSeeItNeedsDebugging();",
      "color": {
        "r": 22,
        "g": 63,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 1,
      "fontSize": 35
    },
    {
      "xCoordinate": 40,
      "yCoordinate": 290,
      "text": "} while(myGuitar.weep(WeepType.GENTLY))",
      "color": {
        "r": 22,
        "g": 63,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 1,
      "fontSize": 35
    }
  ]
}
```
produces output image
![Alt Text](https://github.com/dvorontsov/text-over-image/blob/master/examples/example-1/text-over-image-output.png)
