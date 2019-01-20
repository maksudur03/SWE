
	const functions = require('firebase-functions');
	const admin=require('firebase-admin');
	admin.initializeApp(functions.config().firebase);
	
	exports.sendNotification =functions.database.
	ref('/Notice/2016/{notification_id}').onWrite(event => {
		
		const original = event.after.val();
		const payload = {
        notification:{
            title : original.title,
            body : original.text_,
            badge : '1',
            sound : 'default'
        }
		}
		console.log('We have a notification to send to : ', original.title);
		return admin.database().ref('fcm-token/2016').once('value').then(allToken => {
        if(allToken.val()){
            console.log('token available');
            const token = Object.keys(allToken.val());
            return admin.messaging().sendToDevice(token,payload);
        }else{
            console.log('No token available');
        }
    });
			
	});
	
	
	
	
	



















