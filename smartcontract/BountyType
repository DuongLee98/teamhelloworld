pragma solidity ^0.4.11;

contract BountyType
{
    mapping (string => string) name;
    mapping (string => uint256) rate;
    mapping (string => string) company;
    mapping (string => bool) has;
    string [] symbols;
    
    function addBountytype(string _symbol, string _name, uint256 _rate, string _company) public payable returns(bool)
    {
        if (has[_symbol]==true) return false;
        require(_rate != 0);
        name[_symbol] = _name;
        rate[_symbol] = _rate;
        company[_symbol] = _company;
        symbols.push(_symbol);
        has[_symbol] = true;
        return true;
    }
    
    function checkHasbounty(string _symbol) constant public returns(bool)
    {
        return has[_symbol];
    }
    
    function getName(string u) constant public returns(string)
    {
        bytes memory tmp = bytes(name[u]);
        require(tmp.length != 0);
        return name[u];
    }
    
    function getRate(string u) constant public returns(uint256)
    {
        require(rate[u]!=0);
        return rate[u];
    }
    
    function getCompany(string u) constant public returns(string)
    {
        bytes memory tmp = bytes(name[u]);
        require(tmp.length != 0);
        return company[u];
    }
    
    function getSymbol(uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=symbols.length-1);
        return symbols[i];
    }
    
    function getSizesymbols() constant public returns (uint256)
    {
        return symbols.length;
    }
}
