package tterrag.wailaplugins.plugins;

import java.util.List;

import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class Plugin_IC2 extends PluginBase
{   
	private static final String[] VOLTAGE_NAMES = new String[]{"LV", "MV", "HV", "EV", "UV"};
    
    @Override
    public void load(IWailaRegistrar registrar)
    {
        super.load(registrar);

        registerBody(IEnergySink.class, IEnergySource.class);

    }

    @Override
    protected void getBody(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor)
    {
   		//
        TileEntity tile = accessor.getTileEntity();
			
		if( tile instanceof IEnergySource){
			// ((IEnergySource)tile).getSourceTier()
			currenttip.add(EnumChatFormatting.YELLOW.toString() + "IC2 Emits: " + EnumChatFormatting.GOLD.toString() +  getVoltageTierName(((IEnergySource)tile).getSourceTier()) );
		}
		
		//
		if( tile instanceof IEnergySink){
			currenttip.add(EnumChatFormatting.YELLOW.toString() + "IC2 Accepts: " + EnumChatFormatting.GOLD.toString() + getVoltageTierName(((IEnergySink)tile).getSinkTier()) );
		}
		
		
    }
/*
    @Override
    protected void getNBTData(TileEntity te, NBTTagCompound tag, World world, BlockCoord pos)
    {
       // te.writeToNBT(tag);
    }*/
    
    
    private String getVoltageTierName(int ic2tier){
		int idx = (ic2tier-1);
		
		if(idx >= VOLTAGE_NAMES.length)
		{
			return "ANY";
		}else{
			return VOLTAGE_NAMES[idx];
		}
		
    }
    
}
