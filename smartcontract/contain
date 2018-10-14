pragma solidity ^0.4.11;

contract Contain
{
    struct data 
    {
        uint256 CoinBase;
        uint256 Number;
    }
    mapping(string => mapping(string => data)) product;
    mapping(string => string []) list_product; 
    
    function addProduct(string _symbol, string _id, uint256 _coinbase, uint256 _number) public payable returns(bool)
    {
        if (product[_symbol][_id].Number == 0)
        {
            list_product[_symbol].push(_id);
        }
        product[_symbol][_id].CoinBase = _coinbase;
        product[_symbol][_id].Number = _number;
        return true;
    }
    
    function subProduct(string _id, string _symbol, uint256 _value) public payable returns(bool)
    {
        require(product[_id][_symbol].Number >= _value);
        product[_id][_symbol].Number -= _value;
        return true;
    }
    
    function getCoinBase(string _symbol, string _id) constant public returns(uint256)
    {
        return product[_symbol][_id].CoinBase;
    }
    
    function getNumber(string _symbol, string _id) constant public returns(uint256)
    {
        return product[_symbol][_id].Number;
    }
    
    function getSizeProductCoinSymbol(string _symbol) constant public returns(uint256)
    {
        return list_product[_symbol].length;
    }
    
    function getIdProduct(string _symbol, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_product[_symbol].length-1);
        return list_product[_symbol][i];
    }
}
