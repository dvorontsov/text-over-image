# Text Over Image

## Service Project
https://github.com/dvorontsov/text-over-image/tree/master/text-over-image-service
Project is built as a service that can be used by other applications.  It can be wrapped into a REST endpoint or an executable.

## Executable Project
https://github.com/dvorontsov/text-over-image/tree/master/text-over-image-executable
Project is an executable jar that can be executed from command line with a command line this
```
java -jar jar-file-name input-file.json
```


## Example 
Input file such as 
```
https://github.com/dvorontsov/text-over-image/blob/master/examples/text-over-image-input.json
```
which contains the following json
```
{
  "width": 800,
  "height": 416,
  "color": {
    "r": 245,
    "g": 247,
    "b": 250,
    "a": 255
  },
  "imageLayers": [
    {
      "xCoordinate": 50,
      "yCoordinate": 110,
      "text": "do {",
      "color": {
        "r": 22,
        "g": 63,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 0,
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
      "fontStyle": 0,
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
      "fontStyle": 0,
      "fontSize": 35
    },
    {
      "xCoordinate": 50,
      "yCoordinate": 290,
      "text": "} while(myGuitar.weep(WeepType.GENTLY))",
      "color": {
        "r": 22,
        "g": 63,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 0,
      "fontSize": 35
    }
  ]
}
```
produces output image
![Alt Text](https://github.com/dvorontsov/text-over-image/blob/master/examples/text-over-image-output.png)
