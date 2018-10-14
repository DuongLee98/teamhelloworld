pragma solidity ^0.4.11;

contract Depend
{
    mapping(string => mapping(string => bool)) depend;
    mapping(string => string []) list_level;
    mapping(string => string []) list_gift;
    
    function addDepend(string _gid, string _lid) public payable returns(bool)
    {
        if (depend[_gid][_lid] == false)
        {
            list_level[_gid].push(_lid);
            list_gift[_lid].push(_gid);
        }
        depend[_gid][_lid] = true;
        return true;
    }
    
    
    function getDepend(string _gid, string _lid) constant public returns(bool)
    {
        return depend[_gid][_lid];
    }
    
    
    function getSizeLevel(string _gid) constant public returns(uint256)
    {
        return list_level[_gid].length;
    }
    
    function getIdLevel(string _gid, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_level[_gid].length-1);
        return list_level[_gid][i];
    }
    
    function getSizeGift(string _lid) constant public returns(uint256)
    {
        return list_gift[_lid].length;
    }
    
    function getIdGift(string _lid, uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=list_gift[_lid].length-1);
        return list_gift[_lid][i];
    }
}
