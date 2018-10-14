var socket = require('socket.io')();
var account = require('./coreAccount');
var use = require('./coreUse');
var bountytype = require('./coreBountyType');

const listen_on = 1998;
var rplogin	 =
{
	logged: "1",
	address: "ee857cd58fa0ae57275d6b9f44d0da1799227403",
	name: "Thieu Van Vinh",
	phone: "0936408932",
	reward: 100
}

// var rpuser	 =
// {
// 	hasUser: "1",
// 	rAdress: "d7edd5142a4e3a18899bbffb8b6426d1bd492c19",
// 	rName: "Le Binh Duong",
// 	rPhone: "0983236058"
// }


socket.on('connection',function(client)
{
	console.log('New Connection!');
	// client.emit('vinh', rplogin);
	// account.getPassword("123").then(console.log);
	client.on('create', function(data)
	{
		console.log("user: "+data.user);
		console.log("pass: "+data.pass);
		console.log("phone:"+data.phone);
		let raw = {};

		account.checkHasuser(data.user).then(function(done){
			if (done == true)
			{
				raw.cd = false;
				raw.msg = "Tai Khoan Da Ton Tai";
				console.log(raw);
				client.emit('rpcreate', raw);
			}
			else
			{
				raw.cd = true;
				raw.msg = "Create Thanh Cong";
				console.log(raw);
				client.emit('rpcreate', raw);
			}
		});
	});

	client.on('login', function(data)
	{
		console.log("user: "+data.user);
		console.log('pass: '+data.pass);
		let raw = {};
		account.checkHasuser(data.user).then(function(done){
			if (done == true)
			{
				use.getAllSymbol(data.user).then(function(arrsymbols){
					arrsymbols.reverse();
					raw.symbols = arrsymbols;
					use.getArrAllBounty(data.user, arrsymbols).then(function(arrpsymbols){
						arrpsymbols.reverse();
						raw.allbounty = arrpsymbols;
						console.log(arrpsymbols);
						use.getArrPreBounty(data.user, arrsymbols).then(function(arr){
							arr.reverse();
							raw.balance = arr;
							console.log(arr);
							bountytype.getArrCompany(arrsymbols.length, arrsymbols).then(function(datac){
								datac.reverse();
								raw.company = datac;
								console.log(datac);
								
								raw.cd = true;
								raw.msg = "Thanh Cong";
								console.log(raw);
								client.emit('rplogin', raw);
							});

						});
					});
				});
			}
			else
			{
				raw.cd = false;
				raw.msg = "Tai khoan khong ton tai";
				console.log(raw);
				client.emit('rlogin', raw);
			}
		});
		
	});

	client.on('send', function(data){
		console.log("send");
		console.log("from ", data.from);
		console.log("ftype ", data.ftype);
		console.log("to ", data.to);
		console.log("ttype ", data.ttype);
		console.log("val ", data.val);
		raw = {};
		let from = data.from;
		let ftype = data.ftype;
		let to = data.to;
		let ttype = data.ttype;
		let val = data.val;
		use.getPresentBounty(from, ftype).then(function(coinf){
			let coin1 = parseInt(coinf, 10);
			console.log(coin1);
			bountytype.getRate(ftype).then(function(frate){
				let rate1 = parseInt(frate, 10);
				console.log(rate1);
				use.getPresentBounty(to, ttype).then(function(coint){
					let coin2 = parseInt(coint, 10);
					console.log(coin2);
					bountytype.getRate(ttype).then(function(trate){
						let rate2 = parseInt(trate, 10);
						console.log(rate2);
						let value = parseInt(data.val, 10);

						let nval = (value/rate1)*rate2;
						console.log(nval);

						use.subBounty(from, ftype, value).then(function(n){
							console.log(n);
							use.addBounty(to, ttype, nval).then(function(nn){
								console.log(nn);

								use.getAllSymbol(from).then(function(arrsymbols){
									arrsymbols.reverse();
									raw.symbols = arrsymbols;
									use.getArrAllBounty(from, arrsymbols).then(function(arrpsymbols){
										arrpsymbols.reverse();
										raw.allbounty = arrpsymbols;
										console.log(arrpsymbols);
										use.getArrPreBounty(from, arrsymbols).then(function(arr){
											arr.reverse();
											raw.balance = arr;
											console.log(arr);
											bountytype.getArrCompany(arrsymbols.length, arrsymbols).then(function(datac){
												datac.reverse();
												raw.company = datac;
												console.log(datac);
												
												raw.cd = true;
												raw.msg = "Thanh Cong";
												console.log(raw);
												client.emit('rplogin', raw);
											});

										});
									});
								});

							});
						});
					});
				});
			});
		});
	});

	client.on('data', function(data)
	{
		console.log("user: "+data.user);
		let raw = {};
		account.checkHasuser(data.user).then(function(done){
			if (done == true)
			{
				use.getAllSymbol(data.user).then(function(arrsymbols){
					arrsymbols.reverse();
					raw.symbols = arrsymbols;
					use.getArrAllBounty(data.user, arrsymbols).then(function(arrpsymbols){
						arrpsymbols.reverse();
						raw.allbounty = arrpsymbols;
						console.log(arrpsymbols);
						use.getArrPreBounty(data.user, arrsymbols).then(function(arr){
							arr.reverse();
							raw.balance = arr;
							console.log(arr);
							bountytype.getArrCompany(arrsymbols.length, arrsymbols).then(function(datac){
								datac.reverse();
								raw.company = datac;
								console.log(datac);
								
								raw.cd = true;
								raw.msg = "Thanh Cong";
								console.log(raw);
								client.emit('rplogin', raw);
							});

						});
					});
				});
			}
			else
			{
				raw.cd = false;
				raw.msg = "Tai khoan khong ton tai";
				console.log(raw);
				client.emit('rlogin', raw);
			}
		});
		
	});

	// client.on('sendReward', function(data)
	// {
	// 	console.log('sendReward !');
	// 	console.log('from: '+data.fromAddress);
	// 	console.log('to: '+data.toAddress);
	// 	console.log('value: '+data.value);
	// 	console.log('rpSend ...');
	// 	client.emit('rpSend', makerpSend(data.value));

	// });
});
socket.listen(listen_on);
console.log('app running - listen on ', listen_on);