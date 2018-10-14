const config = require('../config/apiconfig');

let api = new config.InfinitoApi(config.apiConfig);
let wallet = new config.EthWallet(config.walletConfig);
wallet.setApi(api);
let coinAPI = api.ETH;

const contractAdd = '0x692a70d2e424a56d2c6c27aa97d1a86395877b3a';
const abi =
[
	{
		"constant": false,
		"inputs": [
			{
				"name": "_symbol",
				"type": "string"
			},
			{
				"name": "_id",
				"type": "string"
			},
			{
				"name": "_milestone",
				"type": "uint256"
			}
		],
		"name": "addMileStone",
		"outputs": [
			{
				"name": "",
				"type": "bool"
			}
		],
		"payable": true,
		"stateMutability": "payable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_symbol",
				"type": "string"
			},
			{
				"name": "i",
				"type": "uint256"
			}
		],
		"name": "getIdLevel",
		"outputs": [
			{
				"name": "",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_symbol",
				"type": "string"
			},
			{
				"name": "_id",
				"type": "string"
			}
		],
		"name": "getMilestone",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_symbol",
				"type": "string"
			}
		],
		"name": "getSizeLevel",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	}
];

const data = '0x608060405234801561001057600080fd5b5061086d806100206000396000f3006080604052600436106100615763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663a674ffc98114610066578063d0e65528146100d1578063d50106bc14610168578063db542b1a14610208575b600080fd5b34801561007257600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100bf9436949293602493928401919081908401838280828437509497506102d89650505050505050565b60408051918252519081900360200190f35b3480156100dd57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100bf94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506103409650505050505050565b6040805160206004803580820135601f81018490048402850184019095528484526101f494369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975050933594506104049350505050565b604080519115158252519081900360200190f35b34801561021457600080fd5b506040805160206004803580820135601f8101849004840285018401909552848452610263943694929360249392840191908190840183828082843750949750509335945061061b9350505050565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561029d578181015183820152602001610285565b50505050905090810190601f1680156102ca5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60006001826040518082805190602001908083835b6020831061030c5780518252601f1990920191602091820191016102ed565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054949350505050565b600080836040518082805190602001908083835b602083106103735780518252601f199092019160209182019101610354565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842086519094879450925082918401908083835b602083106103cf5780518252601f1990920191602091820191016103b0565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205495945050505050565b600080846040518082805190602001908083835b602083106104375780518252601f199092019160209182019101610418565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842087519094889450925082918401908083835b602083106104935780518252601f199092019160209182019101610474565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054151591506105519050576001846040518082805190602001908083835b602083106104fb5780518252601f1990920191602091820191016104dc565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208054600181018083556000928352918590208851929561054e9550910192508701906107a6565b50505b816000856040518082805190602001908083835b602083106105845780518252601f199092019160209182019101610565565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842088519094899450925082918401908083835b602083106105e05780518252601f1990920191602091820191016105c1565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092209290925550600195945050505050565b6060600082101580156106935750600180846040518082805190602001908083835b6020831061065c5780518252601f19909201916020918201910161063d565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054929092038411159150505b151561069e57600080fd5b6001836040518082805190602001908083835b602083106106d05780518252601f1990920191602091820191016106b1565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092208054909250849150811061070b57fe5b600091825260209182902001805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156107995780601f1061076e57610100808354040283529160200191610799565b820191906000526020600020905b81548152906001019060200180831161077c57829003601f168201915b5050505050905092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107e757805160ff1916838001178555610814565b82800160010185558215610814579182015b828111156108145782518255916020019190600101906107f9565b50610820929150610824565b5090565b61083e91905b80821115610820576000815560010161082a565b905600a165627a7a72305820ac675b49ec52149107dafe6c96fd3438047925441ba37c7a89c2e6efcd1e47790029';
const bounty = new config.web3.eth.Contract(abi, contractAdd);
bounty.options.from = '0x03c33d697509f0eb46844063e27bf079cb973bdd';
bounty.options.gas = 4700000;
bounty.options.data = data;

function addGift(_symbol, _id, _coinbase, _number)
{
	return new Promise(function(resolve, reject)
	{
		function checktransaction(data)
		{
			coinAPI.getTxInfo(data.tx_id).then(function(transactions){
				if (transactions.cd == "1")
				{
					return resolve(false);
				}
				else if (transactions.data.transactions.length == 0)
				{
					return checktransaction(data);
				}
				else
				{
					return resolve(true);
				}
			});
		}
		let txParams = {};
		txParams.sc = {}; 
		txParams.sc.contractAddress = contractAdd;
		txParams.sc.nameFunc = 'addGift';
		txParams.sc.typeParams = ['string', 'string', 'uint256', 'uint256'];
		txParams.sc.paramsFuncs = [_symbol, _id, _coinbase, _number];
		wallet.createRawTx(txParams).then(function(rawTx){
			return wallet.send({
				rawTx: rawTx,
				isBroadCast: true
			});
		}).then(function(data){
			return checktransaction(data);	
		});
	});
}

function subGift(_symbol, _id, _value)
{
	return new Promise(function(resolve, reject)
	{
		function checktransaction(data)
		{
			coinAPI.getTxInfo(data.tx_id).then(function(transactions){
				if (transactions.cd == "1")
				{
					return resolve(false);
				}
				else if (transactions.data.transactions.length == 0)
				{
					return checktransaction(data);
				}
				else
				{
					return resolve(true);
				}
			});
		}
		let txParams = {};
		txParams.sc = {}; 
		txParams.sc.contractAddress = contractAdd;
		txParams.sc.nameFunc = 'subGift';
		txParams.sc.typeParams = ['string', 'string', 'uint256'];
		txParams.sc.paramsFuncs = [_symbol, _id, _value];
		wallet.createRawTx(txParams).then(function(rawTx){
			return wallet.send({
				rawTx: rawTx,
				isBroadCast: true
			});
		}).then(function(data){
			return checktransaction(data);	
		});
	});
}

function getCoinBase(_symbol, _idl)
{
	return new Promise(function(resolve, reject){
		bounty.methods.getCoinBase(_symbol, _idl).call().then(function(data){
			return resolve(data);
		});
	});
}

function getNumber(_symbol, _idl)
{
	return new Promise(function(resolve, reject){
		bounty.methods.getNumber(_symbol, _idl).call().then(function(data){
			return resolve(data);
		});
	});
}

function getSizeProductCoinSymbol(_symbol)
{
	return new Promise(function(resolve, reject){
		bounty.methods.getSizeProductCoinSymbol(_symbol).call().then(function(data){
			return resolve(data);
		});
	});
}

function getIdGift(_symbol, _i)
{
	return new Promise(function(resolve, reject){
		bounty.methods.getIdGift(_symbol, _i).call().then(function(data){
			return resolve(data);
		});
	});
}

function getAllIdGift(_symbol)
{
	return new Promise(function(resolve, reject) {
		var arr = [];
		var end = false;
		function getIdGift(i)
		{
			if (i>=0)
			{
				bounty.methods.getIdGift(_symbol, i).call().then(function(re){
					arr.push(re);
					getIdGift(i-1);
				});
			}
			else
			{
				end = true;
				return resolve(arr);
			}
		}
		bounty.methods.getSizeLevel().call().then(function(re){
			getIdGift(re-1);
		});
	});
}

// addGift("123", "456", 789, 147).then(console.log);
// subGift("123", "456", 123).then(console.log);
// getCoinBase("123", "456").then(console.log);
// getNumber("123", "456").then(console.log);
// getSizeProductCoinSymbol("123").then(console.log);
// getIdGift("123", 0).then(console.log);
// getAllIdGift("123");

module.exports =
{
	addGift: addGift,
	subGift: subGift,
	getCoinBase: getCoinBase,
	getNumber: getNumber,
	getSizeProductCoinSymbol: getSizeProductCoinSymbol,
	getIdGift: getIdGift,
	getAllIdGift: getAllIdGift
}