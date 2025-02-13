/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;

/***
 * An area transformer that takes 0 existing AreaDimensions. Classes implementing
 * this interface are not required to implement any further Transformer interfaces.
 */
public interface IBOPAreaTransformer0
{
    default <R extends IArea> IAreaFactory<R> apply(IExtendedNoiseRandom<R> context)
    {
        if (!(context instanceof IBOPContextExtended))
            throw new IllegalArgumentException("Context must be an IBOPContextExtended");

        IBOPContextExtended<R> bopContext = (IBOPContextExtended<R>)context;

        // Create a new IAreaFactory
        return () ->
            // Return a new IArea, with the below IPixelTransformer
            context.func_212861_a_((x, z) ->
            {
                context.setPosition((long)(x), (long)(z));
                return this.apply(bopContext, x, z);
            });
    }

    int apply(IBOPContextExtended context, int x, int z);
}
