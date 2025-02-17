package com.dostonbek.foodadmin.user


data class User(val name:String,val surname:String,val age:String,val phoneNumber:String,val location: String)
/*var ss = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1c4OEeX5pMlTvSTIVyoyr14DyyaBF8cTVSxWr1BiP8w4/edit?gid=0#gid=0");
var sheet = ss.getSheetByName("register");

function doGet(e) {
  var action = e.parameter.action;
  if (action == "create") {
    return createData(e);
  }
  if (action == "get") {
    return getData(e);
  }
  if(action=="update"){
    return updateData(e);
  }
  if(action=="delete"){
    return deleteData(e);
  }
}
function deleteData(e) {
  var id = e.parameter.id;
  try {
    var row = getRowById(id);
    if (row !== -1) {
      sheet.deleteRow(row);
      return ContentService.createTextOutput("Deleted").setMimeType(ContentService.MimeType.TEXT);
    } else {
      return ContentService.createTextOutput("ID not found").setMimeType(ContentService.MimeType.TEXT);
    }
  } catch (error) {
    return ContentService.createTextOutput("Error: " + error.message).setMimeType(ContentService.MimeType.TEXT);
  }
}
function updateData(e){
  var id=e.parameter.id;
    var name = e.parameter.name;
  var surname = e.parameter.surname;
  var tel = e.parameter.tel;
  var codeA=e.parameter.codeA;
  var codeB=e.parameter.codeB
    var createdTime =e.parameter.createdTime; // Capture current timestamp

  var flag=0;
  for(var i=1;i<=sheet.getLastRow();i++){
    var secondId=sheet.getRange(i,1).getValue();
    if(id==secondId){
      flag=1;
      sheet.getRange(i,2).setValue(name);
      sheet.getRange(i,3).setValue(surname);
      sheet.getRange(i,4).setValue(tel);
      sheet.getRange(i,5).setValue(codeA);
      sheet.getRange(i,6).setValue(codeB);
      sheet.getRange(i,7).setValue(createdTime)
    }
  }
  if(flag==0){
    var result="Not updated";
  }else{
    var result ="Updated";

  }
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.TEXT);
}

function createData(e) {

  var id = sheet.getLastRow() ;

  var name = e.parameter.name;
  var surname = e.parameter.surname;
  var tel = e.parameter.tel;
  var codeA = Math.floor(100000 + Math.random() * 900000);
  var codeB=e.parameter.codeB
    var createdTime = new Date();

 sheet.appendRow([id, name, surname, tel,codeA,codeB,createdTime]);
  return ContentService.createTextOutput("Created").setMimeType(ContentService.MimeType.TEXT);
  }


function getData(e) {
  var records = {};
  var data = [];
  var lastRow = sheet.getLastRow();
  if (lastRow > 1) {
    var rows = sheet.getRange(2, 1, lastRow - 1, sheet.getLastColumn()).getValues();
    for (var r = 0; r < rows.length; r++) {
      var row = rows[r];
      var record = {
        'id':row[0],
        'name': row[1],
        'surname': row[2],
        'tel': row[3],
        'codeA': row[4],
        'codeB': row[5],
        'createdTime': row[6]
      };
      data.push(record);
    }
  }
  records.ListTeacher = data;
  var result = JSON.stringify(records);
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
}
function getRowById(id) {
  var lastRow = sheet.getLastRow();
  var range = sheet.getRange(2, 1, lastRow - 1, 1).getValues(); // Skipping the header row
  for (var i = 0; i < range.length; i++) {
    if (range[i][0] == id) {
      return i + 2; // Adjusting for the header row
    }
  }
  return -1;
}
*/