const { Wallet, CoinType, EthWallet, BchWallet, InfinitoApi, NeoWallet } = require('node-infinito-wallet');
const Web3 = require('web3');
const web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/79KTIDWR8NE2G8CP6I5JBGVAUCFYNWU3RH'));
web3.eth.net.getNetworkType(function(err, res)
{
	console.log("Network Type: "+res);
});

module.exports =
{
	apiConfig:
	{
		apiKey: 'f87aa96a-c237-4fb3-9dfe-542819d02a70',
		secret: 'FB0ZUsC1SxN5TFz6Sc3W20H2DOIrGavi0Txrn3RKEVkgUY6B2yiSLej3Ex16TjdK',
		baseUrl: 'https://staging-api-testnet.infinitowallet.io',
		logLevel: 'NONE'
	},
	walletConfig:
	{
		privateKey: '0x317f20bcc0f8a9863da408d0c8b1116e3742c09e179930408cade260a9004639',
		coinType: CoinType.ETH.symbol,
		address: '0x03c33d697509f0eb46844063e27bf079cb973bdd',
		// publicKey: '0x64ea1094d0a4ba94e51e6bea689a05bacefc3f6d9273d5e9346251ee5af46411e74f4c08565715d6ea9ef8aa14f2bea66cb9630a0844c91065fdae647ef65a29',
		isTestNet: true
	},
	web3: web3,
	EthWallet: EthWallet,
	InfinitoApi: InfinitoApi
}