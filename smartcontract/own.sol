pragma solidity ^0.4.11;

contract Own
{
    struct data 
    {
        uint256 CoinBase;
        uint256 Number;
    }
    mapping(string => mapping(string => data)) gift;
    mapping(string => string []) list_gift; 
    
    function addGift(string _symbol, string _id, uint256 _coinbase, uint256 _number) public payable returns(bool)
    {
        if (gift[_symbol][_id].Number == 0)
        {
            list_gift[_symbol].push(_id);
        }
        gift[_symbol][_id].CoinBase = _coinbase;
        gift[_symbol][_id].Number = _number;
        return true;
    }
    
    function subGift(string _id, string _symbol, uint256 _value) public payable returns(bool)
    {
        require(gift[_id][_symbol].Number >= _value);
        gift[_id][_symbol].Number -= _value;
        return true;
    }
    
    function getCoinBase(string _symbol, string _id) constant public returns(uint256)
    {
        return gift[_symbol][_id].CoinBase;
    }
    
    function getNumber(string _symbol, string _id) constant public returns(uint256)
    {
        return gift[_symbol][_id].Number;
    }
    
    function getSizeProductCoinSymbol(string _symbol) constant public returns(uint256)
    {
        return list_gift[_symbol].length;
    }
    
    function getIdGift(string _symbol, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_gift[_symbol].length-1);
        return list_gift[_symbol][i];
    }
}
