pragma solidity ^0.4.11;

contract MileStone
{
    mapping(string => mapping(string => uint256)) milestone;
    mapping(string => string []) list_level;
    
    function addMileStone(string _symbol, string _id, uint256 _milestone) public payable returns(bool)
    {
        if (milestone[_symbol][_id] == 0)
        {
            list_level[_symbol].push(_id);
        }
        milestone[_symbol][_id] = _milestone;
        return true;
    }
    
    
    function getMilestone(string _symbol, string _id) constant public returns(uint256)
    {
        return milestone[_symbol][_id];
    }
    
    
    function getSizeLevel(string _symbol) constant public returns(uint256)
    {
        return list_level[_symbol].length;
    }
    
    function getIdLevel(string _symbol, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_level[_symbol].length-1);
        return list_level[_symbol][i];
    }
}
