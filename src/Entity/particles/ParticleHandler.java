package Entity.particles;

import Entity.ID;
import Main.Game;

import java.awt.*;

public class ParticleHandler {

    public void CreateParticles(int maxCount, int x, int y, Color color) {
        for (int i = 0; i < maxCount; i++) {
            Game.handler.particles.add(new Particles(x, y, color, ID.Particle));
        }
    }

    public void CreateAmount(int counts, int particlescounts, int x, int y, Color color) {

        for (int i = 0; i < counts; i++) {
            CreateParticles(particlescounts, x + i * 128, y, color);
        }
    }
}
