# WorkFlow
<p>

  <p>

This project is a Workflow App in witch you can create and follow your tasks. Built in Jetpack Compose and Kotlin, 
using Dagger Hilt for dependencies and Room for the local BBDD.
</br>
I also used MVVM and clean architecture for the layout of the project. 
</br> As a curiosity, this app is designed entirely in black and white.

<a href="https://img.shields.io/github/stars/CristipiDev/WorkFlow?style=social">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Give this library a star" src="https://img.shields.io/github/stars/CristipiDev/Pokedex?style=social">
</a>

<a href="https://github.com/CristipiDev/">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Follow me on GitHub" src="https://img.shields.io/github/followers/CristipiDev?style=social&label=Follow">
</a>

# Main page - Menu
The main page in this project is the menu in witch you can select the workflow that you want to see, but also, add, edit and delete the workflows that are already there.
</br>
![addNewWorkflowLight](https://github.com/CristipiDev/WorkFlow/assets/145572177/3799711f-1f89-4459-9e17-8c82a296a860)
![addNewWorkflowDark](https://github.com/CristipiDev/WorkFlow/assets/145572177/bd020704-6318-4115-97f8-c1fd838a1b95)

</br>
At the right top of the page (the "i" button), you will go to the "About us/Info" page. I added a slideIn/OutHorizontal animation to switch between pages:</br>

![aboutUsAnimationLight](https://github.com/CristipiDev/WorkFlow/assets/145572177/37be6fbe-cac0-4f7d-960e-eab770f9d6af)

![aboutUsAnimationDark](https://github.com/CristipiDev/WorkFlow/assets/145572177/7eef276a-04ac-4a1e-bac9-ce7ee3a95087)


</br>
And also at the bottom of the page you will see a custom switch that allows you to switch between themes.</br>

![lightswitch](https://github.com/CristipiDev/WorkFlow/assets/145572177/e5023640-b873-4de2-ac31-884c8256b092)

To navigate from the main page (the menu) to the info page I added this animation:</br>
![navMenuInfo](https://github.com/CristipiDev/WorkFlow/assets/145572177/c4b3ec27-b091-4e8a-a9d6-85ac3c6638d3)

# Info page
In this page you will see and update your tasks.

<table>
  <tr>
    <td><img src="https://i.imgur.com/9dbgBaf.png" title="source: imgur.com" width="300"/></td>
    <td><img src="https://i.imgur.com/Zgn8bir.png" title="source: imgur.com" width="300"/></td>
  </tr>
</table>

And to go back to the menu page you will have to scroll down the top of the info page and you will go back.

![scrollDown](https://github.com/CristipiDev/WorkFlow/assets/145572177/948c61f5-0a9b-42ff-b9db-e53f306b3282)

