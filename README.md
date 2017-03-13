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


# Examples

## Example 1
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


## Example 2
Image generated in Example 1 is used as a background image in this example.
Input file such as 
```
https://github.com/dvorontsov/text-over-image/blob/master/examples/example-2/text-over-image-input.json
```
which contains the following json
```
{
  "height": 416,
  "width": 800,
  "imageSrc" : "examples/example-2/background.png",
  "imageLayers": [
    {
      "type": "color-fill",
      "xCoordinate": 0,
      "yCoordinate": 250,
      "color": {
        "r": 211,
        "g": 211,
        "b": 211,
        "a": 175
      }
    },
    {
      "type": "text",
      "xCoordinate": 40,
      "yCoordinate": 345,
      "text": "This is overlay",
      "color": {
        "r": 100,
        "g": 175,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 1,
      "fontSize": 45
    },
    {
      "type": "text",
      "xCoordinate": 40,
      "yCoordinate": 400,
      "text": "Your ad here! :)",
      "color": {
        "r": 100,
        "g": 175,
        "b": 94,
        "a": 255
      },
      "fontName": "Default",
      "fontStyle": 1,
      "fontSize": 45
    }
  ]
}
```
produces output image
![Alt Text](https://github.com/dvorontsov/text-over-image/blob/master/examples/example-2/text-over-image-output.png)
