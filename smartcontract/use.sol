pragma solidity ^0.4.11;

contract Use
{
    struct data 
    {
        uint256 AllBounty;
        uint256 PresentBounty;
    }
    mapping(string => mapping(string => data)) bounty;
    mapping(string => string []) list_symbol;
    mapping(string => string []) list_user;
    
    function addBounty(string _user, string _symbol, uint256 _value) public payable returns(bool)
    {
        if (bounty[_user][_symbol].AllBounty == 0)
        {
            list_symbol[_user].push(_symbol);
            list_user[_symbol].push(_user);
        }
        bounty[_user][_symbol].AllBounty += _value;
        bounty[_user][_symbol].PresentBounty += _value;
        return true;
    }
    
    function sendBounty(string _user, string _symbol, uint256 _value) public payable returns(bool)
    {
        if (bounty[_user][_symbol].AllBounty == 0)
        {
            list_symbol[_user].push(_symbol);
            list_user[_symbol].push(_user);
        }
        // bounty[_user][_symbol].AllBounty += _value;
        bounty[_user][_symbol].PresentBounty += _value;
        return true;
    }
    
    function subBounty(string _user, string _symbol, uint256 _value) public payable returns(bool)
    {
        require(bounty[_user][_symbol].PresentBounty >= _value);
        bounty[_user][_symbol].PresentBounty -= _value;
        return true;
    }
    
    function getAllBounty(string _user, string _symbol) constant public returns(uint256)
    {
        return bounty[_user][_symbol].AllBounty;
    }
    
    function getPresentBounty(string _user, string _symbol) constant public returns(uint256)
    {
        return bounty[_user][_symbol].PresentBounty;
    }
    
    function getSizesymbols(string _user) constant public returns(uint256)
    {
        return list_symbol[_user].length;
    }
    
    function getSizeusers(string _symbol) constant public returns(uint256)
    {
        return list_user[_symbol].length;
    }
    
    function getSymbol(string _user, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_symbol[_user].length-1);
        return list_symbol[_user][i];
    }
    
    function getUser(string _symbol, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_user[_symbol].length-1);
        return list_user[_symbol][i];
    }
}
