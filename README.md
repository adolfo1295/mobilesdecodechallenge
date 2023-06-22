# mobilesdecodechallenge
 Code challenge

Please follow these instructions to build and run the app:

- Open Android Studio (assuming you have obtained the code from Git correctly).
- Open the app.
- Select File -> Build -> Rebuild Project.
- Run the app.

How to test:

- When you run the app, you will see a list of drivers. Please select any driver.
- The app will open an Alert Dialog that shows the calculated Suitable score.
- Tap the "Ok" button to close the Dialog, or you can tap anywhere outside the Dialog to close it.

How I solved the challenge:

To be honest, I had to Google some things hehe. 
I decided to work with Jetpack Compose because I think it's easy to read, understand, and perform various Android tasks.

To retrieve data from the JSON file, I created a JsonReader located in com/fofito/codeexcerciseapp/utils/JsonReaderHelper.kt.
This class helps us convert the JSON to our model, specifically the ListModel.

After that, I created my ListScreen, added it to MainActivity, and also created my ListViewModel to handle more complex operations.

In the ListScreen, I added a Scaffold, a LazyColumn, and an AlertDialog.

For the algorithm (ViewModel stuff):

I created a calculateSS method that updates the _suitableScoreState, which we collect in our screen.
I separated the Shipment string to extract the street name. For this process, I used the following steps:
Create a list by splitting the string at each space.
Example string: "215 Osinski Manors"
Example list: [215, Osinski, Manors]
I took index 1 to get the street name.
I followed the same process to extract the Driver name:
Example string: "Everardo Welch"
Example list: [Everardo, Welch]
At this point, I have the streetName and DriverName, allowing me to perform the necessary validations:

- Checking if the number is even or odd.
- Checking if the name starts with a vowel.
- Checking if the length of the streetName is equal to the length of the driverName.

And that's pretty much it.

Thanks!

P.S. Sorry for the delay, hehe.