# bixolon-driver

Bixolon's driver for Android *bluetooth* communication **done right**. 
The core of the driver are pure functions/values, which enables *nice composition of actions*.
  
## Show me the example!

Sure thing. But you know you need to connect the printer, right?
Enable bluetooth, scan for devices (while your printer is on), choose the device, enter the password (default is 0000), and then you can try the examples.

I suggest you run the **integration test** while the printer is on to confirm that it's working. You need to have paper inside, right? Right. 

**If unclear or not working, consult the Bixiolon printer manual(s).**

### Text print example

```java

    final Printer printer = TextPrinterBuilder
            .aPrinterBuilder()
            .withCodePage( CodePage.CP_437_USA )
            .withGeneralControlSequence( Alignment.LEFT )
            .withTextControlSequence( CharacterSize.NORMAL )
            .withTextControlSequence( DeviceFont.DEVICE_FONT_A )
            .buildPrinter( textTextContent.toString() );
    
    bluetoothSPP.send( printer.getCommand(), false );
    
```

### QR print example

```java

    final Printer printer = QrPrinterBuilder
            .aPrinterBuilder()
            .withGeneralControlSequence( Alignment.CENTER )
            .withQrControlSequence( QrCodeModel.MODEL2 )
            .withQrControlSequence( QrCodeSize.SIZE7 )
            .withQrControlSequence( QrCodeErrorCorrectionLevel.L )
            .buildPrinter( qrTextContent.toString() );
    
    bluetoothSPP.send( printer.getCommand(), false );
    
```

### MSR example

```java

    bluetoothSPP.setOnDataReceivedListener( new BluetoothSPP.OnDataReceivedListener() {
        public void onDataReceived( byte[] data, String message ) {
            // Do something when data incoming
        }
    } );
    
```

### Atomic printing example

```java

    final Printer textLeftPrinter = TextPrinterBuilder
            .aPrinterBuilder()
            .withGeneralControlSequence( Alignment.LEFT )
            .buildPrinter( "test" );
    
    final Printer qrPrinter = QrPrinterBuilder
            .aPrinterBuilder()
            .withQrControlSequence( QrCodeModel.MODEL2 )
            .withQrControlSequence( QrCodeSize.SIZE7 )
            .withQrControlSequence( QrCodeErrorCorrectionLevel.L )
            .buildPrinter( "223175087923687075112234402528973166755123456781508151013321" );
    
    // it composes
    final Printer combinedPrinter = TextPrinterBuilder
            .aPrinterBuilder()
            .withControlSequences( textLeftPrinter )
            .withControlSequences( qrPrinter )
            .buildPrinter( "test" );
    
    bluetoothSPP.send( combinedPrinter.getCommand(), false );
    
```

### Further examples

Be sure to checkout the example module that contains a simple Activity that has the basic functionality, along with
**integration tests**.
  
## Why?

Since 2013 I had a lot of problems with Android Bixolon driver, and spent an enormous time trying to fix the problems it produced.
And when I say an enormous amount of time, **I mean months**! If you didn't work with the original driver ( **versioned 1.6.2** ),
you don't know what I'm talking about. It would stop working out of the blue, and then you had to turn it off and on again (like all the great software products).
  
The final drop was the newest requirement to print the receipt atomically (in one go, combining serveral other
receipts/QR codes into one).

I decieded to finally take my time and do this right. And what I found out was, brace for it - it took me two weeks
to write a driver that actually works, does the job better, is tested, and understandable.

I do understand that without the existing documentation the job would have been really difficult, but all the same.

## What printer models?

I have tested it on:

- SPP-R200II
- SPP-300

And it should work on any other models using the same "control codes" - same byte sequence for the commands.

## Should I use it?

If you are using a ***bluetooth connection*** along with these things:
 
- TEXT printing
- QR printing
- MSR

Yes. We are using it in production.
I encourage you to add the missing functionality that shouldn't be that hard using the 'command manual'.

## Android version?

This library acutally works from version **API 9** (because it uses 'Android-BluetoothSPPLibrary' that is able to work that low),
no additional libraries required, no class exclusions, warnings, errors. 
I have personally tested it on **API 10**, and it *JUST WORKS*.

## Details

You can check out Bixolon's (decent) documentation PDF called 'manual_spp-r200ii_command_english_rev_1_01.pdf' for details.

## External library

I spent some time writing my own bluetooth communication classes, and they worked, but a lot of problems remained.
**It's not impossible, just very difficult.** 

So, I used this (great!) library that completly handles the bluetooth communication:

- [Android-BluetoothSPPLibrary](https://github.com/akexorcist/Android-BluetoothSPPLibrary)

So, if you use/like this library, be sure to thank @akexorcist.

## Deploy?

- http://inthecheesefactory.com/blog/how-to-upload-library-to-jcenter-maven-central-as-dependency/en

## License

Copyright (c) 2016 Istratech

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
