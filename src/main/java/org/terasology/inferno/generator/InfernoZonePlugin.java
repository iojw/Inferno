/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.inferno.generator;

import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.engine.world.zones.ConstantLayerThickness;
import org.terasology.engine.world.zones.LayeredZoneRegionFunction;
import org.terasology.engine.world.zones.ZonePlugin;
import org.terasology.inferno.generator.providers.CaveFacetProvider;
import org.terasology.inferno.generator.providers.CaveToDensityProvider;
import org.terasology.inferno.generator.providers.ElevationProvider;
import org.terasology.inferno.generator.providers.FloraProvider;
import org.terasology.inferno.generator.providers.InfernalTreeProvider;
import org.terasology.inferno.generator.providers.InfernoCeilingProvider;
import org.terasology.inferno.generator.providers.InfernoSurfaceProvider;
import org.terasology.inferno.generator.providers.LavaFallsProvider;
import org.terasology.inferno.generator.providers.LavaHutProvider;
import org.terasology.inferno.generator.providers.LavaLevelProvider;
import org.terasology.inferno.generator.rasterizers.CaveRasterizer;
import org.terasology.inferno.generator.rasterizers.InfernalTreeRasterizer;
import org.terasology.inferno.generator.rasterizers.InfernoFloraRasterizer;
import org.terasology.inferno.generator.rasterizers.InfernoWorldRasterizer;
import org.terasology.inferno.generator.rasterizers.LavaFallsRasterizer;
import org.terasology.inferno.generator.rasterizers.LavaHutRasterizer;

import static org.terasology.engine.world.zones.LayeredZoneRegionFunction.LayeredZoneOrdering.DEEP_UNDERGROUND;

@RegisterPlugin
public class InfernoZonePlugin extends ZonePlugin {
    public static final int INFERNO_DEPTH = 100000;
    public static final int INFERNO_HEIGHT = 35;
    public static final int INFERNO_BORDER = 20000;

    public InfernoZonePlugin() {
        super("Inferno", new LayeredZoneRegionFunction(new ConstantLayerThickness(200_200), DEEP_UNDERGROUND));

        // Inferno
        addProvider(new InfernoSurfaceProvider(INFERNO_DEPTH));
        addProvider(new InfernoCeilingProvider(INFERNO_HEIGHT));
        addProvider(new ElevationProvider());
        addProvider(new LavaLevelProvider());
        addProvider(new LavaFallsProvider());
        addProvider(new FloraProvider());
        addProvider(new InfernalTreeProvider());
        addProvider(new LavaHutProvider());
        addRasterizer(new InfernoWorldRasterizer());
        // Caves rasterized right after main rasterizer
        addRasterizer(new CaveRasterizer());
        addRasterizer(new InfernoFloraRasterizer());
        addRasterizer(new LavaHutRasterizer());
        addRasterizer(new InfernalTreeRasterizer());
        addRasterizer(new LavaFallsRasterizer());
        // Caves
        addProvider(new CaveFacetProvider());
        addProvider(new CaveToDensityProvider());
    }
}



