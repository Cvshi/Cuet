package com.example.data.model

object GeophysicsQuestionBank {

    val questions: List<Question> = listOf(
        // CHAPTER 1: Foundations of Classical Mechanics
        Question(
            id = 1,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Galilean Transformation",
            questionText = "Under a Galilean transformation, x' = x - vt, t' = t, which of the following physical quantities is NOT invariant?",
            options = listOf(
                "The acceleration of a particle, a",
                "The force between two particles, F₁₂",
                "The velocity of a particle, u",
                "The mass of a particle, m"
            ),
            correctIndex = 2,
            explanation = "Under the Galilean transformation x' = x - vt and t' = t, differentiating with respect to time yields u' = u - v. Hence velocity depends on the reference frame and is NOT invariant. Differentiating again gives a' = a (invariant). In classical mechanics, mass m is constant, so force F = ma is also invariant.",
            formulaTakeaway = "u' = u - v (frame-dependent) | a' = a, F' = F, m' = m (invariant)"
        ),
        Question(
            id = 2,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Inertial Frames",
            questionText = "The statement that the laws of mechanics are the same in all inertial frames of reference is known as:",
            options = listOf(
                "The principle of equivalence",
                "Newton's first law",
                "The principle of Galilean relativity",
                "The principle of superposition"
            ),
            correctIndex = 2,
            explanation = "The Principle of Galilean Relativity (or Newtonian Relativity) asserts that Newton's laws of mechanics have exactly the same mathematical form in every inertial reference frame. No mechanical experiment performed inside an inertial frame can detect its uniform linear motion.",
            formulaTakeaway = "Principle of Relativity: Laws of mechanics have identical form in all inertial frames S and S'"
        ),
        Question(
            id = 3,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Galilean Transformation",
            questionText = "A particle has velocity u in an inertial frame S. In another inertial frame S' moving with a constant velocity v relative to S along the x-axis, the velocity u' is given by:",
            options = listOf(
                "u' = u + v",
                "u' = u - v",
                "u' = v - u",
                "u' = √(u² - v²)"
            ),
            correctIndex = 1,
            explanation = "Starting with position r' = r - vt, taking the time derivative with t' = t gives dr'/dt' = dr/dt - v, hence u' = u - v.",
            formulaTakeaway = "u' = u - v"
        ),
        Question(
            id = 4,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Newton's Laws",
            questionText = "If Newton's second law, F = ma, holds in an inertial frame S, what is the form of the law in a frame S' moving with constant velocity v relative to S?",
            options = listOf(
                "F' = ma' + m(dv/dt)",
                "F' = ma', where F' ≠ F",
                "F' = ma', where F' = F",
                "The law does not hold its form"
            ),
            correctIndex = 2,
            explanation = "Because S' moves with constant velocity v, dv/dt = 0. Therefore a' = a. Since interactions depend on relative coordinates |r₁ - r₂| which are invariant, F' = F. Hence F' = ma' holds identically.",
            formulaTakeaway = "F = ma ⟺ F' = ma' (form-invariant)"
        ),
        Question(
            id = 5,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Relativity",
            questionText = "Maxwell's equations of electromagnetism are NOT invariant under Galilean transformations. This fundamental inconsistency historically led to:",
            options = listOf(
                "Quantum Mechanics",
                "General Theory of Relativity",
                "Special Theory of Relativity",
                "Fluid Dynamics"
            ),
            correctIndex = 2,
            explanation = "Maxwell's equations predict that the speed of light c is an invariant constant in vacuum. Under Galilean velocity addition, c would vary by c' = c - v. This contradiction led Albert Einstein (1905) to replace Galilean transformations with the Lorentz transformations in the Special Theory of Relativity.",
            formulaTakeaway = "Lorentz transformation: x' = γ(x - vt), t' = γ(t - vx/c²)"
        ),
        Question(
            id = 6,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Conservation Laws",
            questionText = "Under Galilean transformation, the change in kinetic energy of a particle of mass m with initial velocity u in frame S, viewed from frame S' moving with velocity v, is ΔK = K' - K. What is ΔK?",
            options = listOf(
                "0",
                "½ m v²",
                "-m(u · v) + ½ m v²",
                "m(u · v)"
            ),
            correctIndex = 2,
            explanation = "K' = ½ m (u')² = ½ m (u - v)² = ½ m (u² - 2 u · v + v²) = ½ m u² - m (u · v) + ½ m v². Therefore ΔK = K' - K = -m(u · v) + ½ m v². Kinetic energy is frame-dependent, though total energy conservation holds in all inertial frames.",
            formulaTakeaway = "K' = K - m(u · v) + ½ m v²"
        ),
        Question(
            id = 7,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Fictitious Forces",
            questionText = "An observer in a train moving with constant linear acceleration a₀ observes a pendulum of mass m. What is the fictitious (pseudo) force acting on the pendulum bob?",
            options = listOf(
                "mg",
                "m a₀",
                "-m a₀",
                "0"
            ),
            correctIndex = 2,
            explanation = "In an accelerating reference frame with acceleration a₀, Newton's second law requires an inertial (fictitious) force F_pseudo = -m a₀ directed opposite to the acceleration of the frame.",
            formulaTakeaway = "F_fictitious = -m a₀"
        ),
        Question(
            id = 8,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Rotating Frames & Coriolis",
            questionText = "The Coriolis force on a particle of mass m moving with velocity v_r relative to a frame rotating with angular velocity ω is given by:",
            options = listOf(
                "-m (ω × v_r)",
                "-2m (ω × v_r)",
                "-m ω × (ω × r)",
                "-2m (v_r × r)"
            ),
            correctIndex = 1,
            explanation = "The Coriolis force is given by F_c = -2m (ω × v_r). It acts perpendicular to both the rotation axis ω and the relative velocity v_r of the particle, deflecting moving bodies to the right in the Northern Hemisphere and to the left in the Southern Hemisphere.",
            formulaTakeaway = "F_Coriolis = -2m (ω × v_r)"
        ),
        Question(
            id = 9,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Rotating Frames",
            questionText = "The centrifugal force on a particle of mass m at position vector r from the axis in a frame rotating with angular velocity ω is:",
            options = listOf(
                "-m (ω · r) ω",
                "-2m (ω × r)",
                "-m ω × (ω × r)",
                "-m (dω/dt) × r"
            ),
            correctIndex = 2,
            explanation = "The centrifugal force vector formula is F_cf = -m ω × (ω × r). Using vector triple product identity, this points radially outwards from the axis of rotation with magnitude m ω² r_⊥.",
            formulaTakeaway = "F_centrifugal = -m ω × (ω × r) = m ω² r_⟂"
        ),
        Question(
            id = 10,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Geophysics & Earth Rotation",
            questionText = "The Foucault pendulum provides direct experimental proof for:",
            options = listOf(
                "The Earth's revolution around the Sun",
                "The Earth's axial rotation",
                "The equivalence principle",
                "Gravitational wave propagation"
            ),
            correctIndex = 1,
            explanation = "A Foucault pendulum oscillates in a plane that rotates relative to the Earth due to the Coriolis force. The period of rotation of the plane of oscillation is T = 24 / sin(λ) hours, where λ is the latitude, directly proving the Earth's daily rotation on its axis.",
            formulaTakeaway = "T_Foucault = 24 / sin(λ) hours | North Pole: 24h, Equator: ∞ (no rotation)"
        ),
        Question(
            id = 11,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Equivalence Principle",
            questionText = "The Weak Equivalence Principle (WEP) establishes the exact proportionality (equivalence) between:",
            options = listOf(
                "Inertial mass and rest energy",
                "Gravitational mass and inertial mass",
                "Active and passive charge",
                "Linear and angular momentum"
            ),
            correctIndex = 1,
            explanation = "The Weak Equivalence Principle states that the inertial mass (resistance to acceleration, F = m_i a) and gravitational mass (coupling to gravity, F = m_g g) are identical: m_i = m_g. This ensures all bodies fall with the same acceleration in a gravitational field.",
            formulaTakeaway = "m_inertial = m_gravitational ⟹ a = g (independent of composition)"
        ),
        Question(
            id = 12,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Equivalence Principle",
            questionText = "According to Einstein's elevator thought experiment, an observer in a closed elevator cannot distinguish between a uniform gravitational field g and:",
            options = listOf(
                "A uniform velocity v",
                "A uniform upward acceleration a = g in gravity-free space",
                "A uniform angular rotation",
                "A static electric field"
            ),
            correctIndex = 1,
            explanation = "In a closed chamber, the effects of a downward gravitational field g are indistinguishable from an upward uniform acceleration a = g in free space. Both produce identical downward acceleration of all test particles.",
            formulaTakeaway = "Uniform gravity g ≡ Uniform acceleration a = -g"
        ),
        Question(
            id = 13,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Orbital Mechanics",
            questionText = "The escape velocity v_e of a projectile of mass m from the surface of a spherical planet of mass M and radius R is:",
            options = listOf(
                "√(GM/R)",
                "√(2GM/R)",
                "2√(GM/R)",
                "GM/R²"
            ),
            correctIndex = 1,
            explanation = "Setting total mechanical energy equal to zero at infinity: ½ m v_e² - G M m / R = 0 ⟹ v_e = √(2GM/R) = √(2gR). For Earth, v_e ≈ 11.2 km/s.",
            formulaTakeaway = "v_escape = √(2GM/R) = √(2gR) = √2 · v_orbital"
        ),
        Question(
            id = 14,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Coriolis Effect",
            questionText = "A river flows from North to South in the Northern Hemisphere. Due to the Coriolis effect, which bank experiences greater erosion?",
            options = listOf(
                "The East bank (left bank)",
                "The West bank (right bank)",
                "Both banks equally",
                "Neither bank"
            ),
            correctIndex = 1,
            explanation = "In the Northern Hemisphere, the Coriolis force F_c = -2m(ω × v) acts to the right of the direction of motion. For water flowing South, looking in the direction of motion (South), the right side is West. Hence the West bank is eroded more.",
            formulaTakeaway = "Northern Hemisphere Coriolis deflection: Always to the RIGHT of motion"
        ),

        // CHAPTER 2: Conservation Laws in Mechanics
        Question(
            id = 15,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Linear Momentum",
            questionText = "The law of conservation of linear momentum for an isolated system is a direct mathematical consequence of the:",
            options = listOf(
                "Homogeneity of time",
                "Homogeneity of space (translational symmetry)",
                "Isotropy of space (rotational symmetry)",
                "Equivalence principle"
            ),
            correctIndex = 1,
            explanation = "According to Noether's theorem, every continuous symmetry of the Lagrangian corresponds to a conservation law. Translational symmetry in space (homogeneity of space) yields conservation of linear momentum. Time translation symmetry yields energy conservation, and spatial rotational symmetry yields angular momentum conservation.",
            formulaTakeaway = "Homogeneity of Space ⟹ Linear Momentum Conservation (Noether's Theorem)"
        ),
        Question(
            id = 16,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Elastic Collisions",
            questionText = "A particle of mass m₁ moving with velocity u₁ collides head-on elastically with a stationary particle of mass m₂ (u₂ = 0). Under what condition is maximum kinetic energy transferred to m₂?",
            options = listOf(
                "m₁ >> m₂",
                "m₁ << m₂",
                "m₁ = m₂",
                "m₁ = 2 m₂"
            ),
            correctIndex = 2,
            explanation = "In a 1D elastic collision with u₂ = 0, the fraction of kinetic energy transferred to m₂ is ΔK/K₁ = 4m₁m₂ / (m₁ + m₂)². Maximizing this expression with respect to m₂ gives d/dm₂ [4m₁m₂/(m₁+m₂)²] = 0, which yields m₁ = m₂. In this case, 100% of the KE is transferred and m₁ comes to rest!",
            formulaTakeaway = "Max KE transfer in elastic collision occurs when m₁ = m₂ (100% transfer)"
        ),
        Question(
            id = 17,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Inelastic Collisions",
            questionText = "For a perfectly inelastic collision between two bodies, the coefficient of restitution e is:",
            options = listOf(
                "e = 1",
                "e = 0.5",
                "e = 0",
                "e = ∞"
            ),
            correctIndex = 2,
            explanation = "The coefficient of restitution is defined as e = |v₂ - v₁| / |u₁ - u₂| (relative speed of separation over relative speed of approach). In a perfectly inelastic collision, the bodies stick together and move with the same final velocity, so v₂ - v₁ = 0, meaning e = 0.",
            formulaTakeaway = "e = 1 (Elastic) | 0 < e < 1 (Partially inelastic) | e = 0 (Perfectly inelastic)"
        ),
        Question(
            id = 18,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Variable Mass Systems",
            questionText = "A rocket operates in gravity-free space. If exhaust gases are ejected at constant relative speed v_e, what is Tsiolkovsky's rocket equation for final velocity v starting from rest with initial mass m₀ and final empty mass m_f?",
            options = listOf(
                "v = v_e (m₀ / m_f)",
                "v = v_e ln(m₀ / m_f)",
                "v = v_e ln(m_f / m₀)",
                "v = ½ v_e (m₀ / m_f)²"
            ),
            correctIndex = 1,
            explanation = "The variable mass rocket equation is m dv = -v_e dm. Integrating from m₀ to m_f with v(0)=0 gives ∫ dv = -v_e ∫ dm/m = v_e ln(m₀/m_f).",
            formulaTakeaway = "Δv = v_e ln(m₀ / m_f) (Tsiolkovsky Equation)"
        ),
        Question(
            id = 19,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Variable Mass & Thrust",
            questionText = "A rocket burns fuel at a constant rate dm/dt = -λ with relative exhaust velocity v_e. What is the instantaneous thrust force exerted on the rocket?",
            options = listOf(
                "F_thrust = λ v_e",
                "F_thrust = ½ λ v_e²",
                "F_thrust = λ / v_e",
                "F_thrust = m λ v_e"
            ),
            correctIndex = 0,
            explanation = "By momentum conservation for a variable mass system, the thrust force F_thrust is the reaction force due to ejecting mass: F_thrust = v_rel |dm/dt| = λ v_e.",
            formulaTakeaway = "F_thrust = v_e · |dm/dt|"
        ),
        Question(
            id = 20,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Conservative Forces",
            questionText = "A force field F(r) is conservative if and only if which of the following conditions is satisfied?",
            options = listOf(
                "∇ · F = 0",
                "∇ × F = 0",
                "∂F/∂t ≠ 0",
                "∮ F · dr ≠ 0"
            ),
            correctIndex = 1,
            explanation = "A force is conservative if the work done around any closed loop is zero: ∮ F · dr = 0. By Stokes' theorem, ∮ F · dr = ∬ (∇ × F) · dS = 0 for any surface, which requires ∇ × F = 0 (the curl of F is zero). In this case, F can be expressed as the negative gradient of a potential, F = -∇U.",
            formulaTakeaway = "Conservative Force: ∇ × F = 0 ⟺ F = -∇U ⟺ ∮ F · dr = 0"
        ),
        Question(
            id = 21,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Central Force & Angular Momentum",
            questionText = "In any central force field F(r) = f(r) r̂, which physical quantity is strictly conserved for an orbiting particle?",
            options = listOf(
                "Linear momentum only",
                "Kinetic energy only",
                "Total angular momentum L",
                "Potential energy only"
            ),
            correctIndex = 2,
            explanation = "The torque on a particle is τ = r × F. In a central force field, F is parallel to r (directed along r̂), so r × F = 0. Since τ = dL/dt, τ = 0 implies that angular momentum L is a constant of motion. This also proves Kepler's second law (areal velocity dA/dt = L/(2m) = const).",
            formulaTakeaway = "τ = r × F = 0 ⟹ L = r × p = constant (Areal velocity dA/dt = L/(2m) = const)"
        ),
        Question(
            id = 22,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Center of Mass Frame",
            questionText = "In the Center of Mass (CM) reference frame of a two-particle system with masses m₁ and m₂, the total linear momentum is:",
            options = listOf(
                "(m₁ + m₂) v_cm",
                "m₁ v₁ + m₂ v₂",
                "Always zero",
                "Dependent on initial energy"
            ),
            correctIndex = 2,
            explanation = "By definition, the position of CM is R_cm = (m₁r₁ + m₂r₂)/(m₁+m₂). Differentiating gives P_total = M V_cm. In the CM frame, the origin moves with V_cm, so V'_cm = 0, and total momentum P'_total = 0 identically (hence called the zero-momentum frame).",
            formulaTakeaway = "P_total(CM frame) = 0"
        ),
        Question(
            id = 23,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Work-Energy Theorem",
            questionText = "A bomb of mass M at rest explodes into two pieces of masses m₁ and m₂. If their kinetic energies are K₁ and K₂ respectively, what is the ratio K₁ / K₂?",
            options = listOf(
                "m₁ / m₂",
                "m₂ / m₁",
                "(m₂ / m₁)²",
                "√(m₂ / m₁)"
            ),
            correctIndex = 1,
            explanation = "Since the bomb was at rest and no external force acts, total momentum is conserved: p₁ + p₂ = 0 ⟹ |p₁| = |p₂| = p. Kinetic energy is K = p² / (2m). Therefore K₁ = p²/(2m₁) and K₂ = p²/(2m₂). Taking the ratio gives K₁ / K₂ = m₂ / m₁ (the lighter fragment carries more kinetic energy!).",
            formulaTakeaway = "Equal momentum ⟹ K ∝ 1/m ⟹ K₁ / K₂ = m₂ / m₁"
        ),

        // CHAPTER 3: Rotational Dynamics
        Question(
            id = 24,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Moment of Inertia",
            questionText = "The moment of inertia of a uniform solid sphere of mass M and radius R about its diameter is:",
            options = listOf(
                "½ M R²",
                "⅔ M R²",
                "⅖ M R²",
                "¾ M R²"
            ),
            correctIndex = 2,
            explanation = "For a uniform solid sphere of mass M and radius R, integration over concentric spherical shells gives I_diameter = (2/5) M R². (Note: for a hollow spherical shell it is (2/3) M R²).",
            formulaTakeaway = "I_solid_sphere = ⅖ MR² | I_hollow_sphere = ⅔ MR²"
        ),
        Question(
            id = 25,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Parallel Axis Theorem",
            questionText = "The moment of inertia of a uniform solid sphere of mass M and radius R about a tangent axis is:",
            options = listOf(
                "⅖ M R²",
                "⅗ M R²",
                "⁷/₅ M R²",
                "⁹/₅ M R²"
            ),
            correctIndex = 2,
            explanation = "By the Parallel Axis Theorem, I_tangent = I_cm + M d², where d = R. Here I_cm = (2/5) M R², so I_tangent = ⅖ M R² + M R² = ⁷/₅ M R².",
            formulaTakeaway = "I_tangent = ⅖ MR² + MR² = ⁷/₅ MR²"
        ),
        Question(
            id = 26,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Radius of Gyration",
            questionText = "A body of mass M has a moment of inertia I about a given axis. Its radius of gyration K is defined by:",
            options = listOf(
                "K = I / M",
                "K = √(I / M)",
                "K = √(M / I)",
                "K = I / M²"
            ),
            correctIndex = 1,
            explanation = "The radius of gyration K is the effective distance from the axis of rotation where the entire mass M could be concentrated without altering its moment of inertia: I = M K² ⟹ K = √(I / M).",
            formulaTakeaway = "K = √(I / M)"
        ),
        Question(
            id = 27,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Moments of Inertia",
            questionText = "The moment of inertia of a thin uniform rod of mass M and length L about an axis perpendicular to the rod passing through its center is:",
            options = listOf(
                "ML² / 3",
                "ML² / 12",
                "ML² / 6",
                "ML² / 24"
            ),
            correctIndex = 1,
            explanation = "Integrating dI = x² dm with dm = (M/L) dx from x = -L/2 to +L/2 gives I = (M/L) [x³/3]_{-L/2}^{L/2} = (M/L) (L³/24 + L³/24) = ML² / 12. (About one end, using parallel axis theorem: ML²/12 + M(L/2)² = ML²/3).",
            formulaTakeaway = "I_rod_center = ML² / 12 | I_rod_end = ML² / 3"
        ),
        Question(
            id = 28,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Rolling Motion",
            questionText = "A solid sphere, a solid cylinder, and a thin circular ring, each having the same mass M and outer radius R, roll down an inclined plane without slipping. In what order do they reach the bottom?",
            options = listOf(
                "Ring first, then cylinder, then sphere",
                "Sphere first, then cylinder, then ring",
                "All reach simultaneously",
                "Cylinder first, then sphere, then ring"
            ),
            correctIndex = 1,
            explanation = "The linear acceleration of rolling down an incline is a = g sin(θ) / (1 + I/(MR²)) = g sin(θ) / (1 + K²/R²). For a solid sphere, K²/R² = 0.4 (highest acceleration, a = 5/7 g sin θ). For a solid cylinder, K²/R² = 0.5 (a = 2/3 g sin θ). For a ring, K²/R² = 1.0 (lowest acceleration, a = 1/2 g sin θ). Thus sphere arrives first, then cylinder, then ring.",
            formulaTakeaway = "a_rolling = g sin θ / (1 + K²/R²) ⟹ Sphere (0.4) > Cylinder (0.5) > Ring (1.0)"
        ),
        Question(
            id = 29,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Lissajous Figures",
            questionText = "Two simple harmonic motions of equal frequency ω and amplitudes A and B act along perpendicular axes: x = A sin(ωt + δ) and y = B sin(ωt). If the phase difference δ = π/2, the resulting Lissajous figure is:",
            options = listOf(
                "A straight line with positive slope",
                "A parabola",
                "An ellipse with principal axes along the coordinate axes",
                "A figure of eight"
            ),
            correctIndex = 2,
            explanation = "When δ = π/2, x = A cos(ωt) ⟹ x/A = cos(ωt) and y/B = sin(ωt). Squaring and adding gives (x/A)² + (y/B)² = cos²(ωt) + sin²(ωt) = 1. This is the equation of an ellipse centered at origin with axes aligned with coordinate axes. If A = B, it becomes a circle!",
            formulaTakeaway = "Equal frequency & δ = π/2 ⟹ Ellipse (or Circle if A = B); δ = 0, π ⟹ Straight line"
        ),
        Question(
            id = 30,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Beats & Acoustics",
            questionText = "Two tuning forks produce 4 beats per second. When the prong of one fork (frequency 256 Hz) is loaded with wax, the beat frequency decreases to 2 beats per second. What was the frequency of the other fork?",
            options = listOf(
                "252 Hz",
                "260 Hz",
                "254 Hz",
                "258 Hz"
            ),
            correctIndex = 1,
            explanation = "Beat frequency is |f₁ - f₂| = 4. With f₁ = 256 Hz, f₂ could be 252 Hz or 260 Hz. Loading a tuning fork with wax increases its inertia, lowering its frequency (f₁' < 256). If f₂ was 252 Hz, decreasing f₁ would make |f₁' - 252| smaller or larger? As f₁ drops from 256 to say 254, beat frequency with 252 would drop from 4 to 2! If f₂ = 260 Hz, as f₁ drops below 256, beat frequency with 260 would increase (from 4 to 6). Therefore the original beat frequency decreasing means f₁ was higher than f₂, wait: if f₁ drops, difference with 252 becomes (254 - 252) = 2 (decreases). So f₂ = 252 Hz.",
            formulaTakeaway = "f_beat = |f₁ - f₂| | Loading fork with wax DECREASES its frequency"
        ),
        Question(
            id = 31,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Rotational Kinetic Energy",
            questionText = "A flywheel of moment of inertia 2.0 kg·m² rotates at an angular velocity of 30 rad/s. Its rotational kinetic energy is:",
            options = listOf(
                "60 J",
                "450 J",
                "900 J",
                "1800 J"
            ),
            correctIndex = 2,
            explanation = "Rotational kinetic energy is given by K_rot = ½ I ω² = ½ · 2.0 kg·m² · (30 rad/s)² = 1.0 · 900 = 900 J.",
            formulaTakeaway = "K_rot = ½ I ω² = L² / (2I)"
        ),
        Question(
            id = 32,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Perpendicular Axis Theorem",
            questionText = "The perpendicular axis theorem I_z = I_x + I_y is valid strictly for:",
            options = listOf(
                "Any 3D arbitrary solid body",
                "Spherically symmetric bodies only",
                "Planar laminar (two-dimensional) bodies only",
                "Cylinders and cones only"
            ),
            correctIndex = 2,
            explanation = "The perpendicular axis theorem states that for a flat laminar body lying in the x-y plane (where z = 0 for all mass elements), I_z = ∫ (x² + y²) dm = ∫ y² dm + ∫ x² dm = I_x + I_y. It is strictly limited to 2D planar laminas.",
            formulaTakeaway = "I_z = I_x + I_y (Planar lamina only; z coordinate must be zero)"
        ),
        Question(
            id = 33,
            chapter = 1,
            chapterTitle = "Foundations of Classical Mechanics",
            topic = "Gravitational Redshift",
            questionText = "When a light photon of initial frequency ν₀ climbs out of a gravitational potential well of depth GM/R, its observed frequency ν is:",
            options = listOf(
                "Higher (blue-shifted)",
                "Lower (red-shifted)",
                "Unchanged because light speed is constant",
                "Zero"
            ),
            correctIndex = 1,
            explanation = "As a photon travels upward against a gravitational field, it loses gravitational potential energy (E = hν). Since the photon's energy decreases, its frequency decreases: ν = ν₀ (1 - GM/(R c²)). This is known as gravitational redshift, a hallmark prediction of Einstein's equivalence principle.",
            formulaTakeaway = "Δν / ν = -ΔΦ / c² = -g h / c² (Gravitational Redshift)"
        ),
        Question(
            id = 34,
            chapter = 2,
            chapterTitle = "Conservation Laws in Mechanics",
            topic = "Center of Mass",
            questionText = "Two skaters with masses 40 kg and 60 kg hold opposite ends of a 10-meter light rope on frictionless ice. When they pull themselves together, how far does the 40 kg skater move before they meet?",
            options = listOf(
                "4.0 m",
                "5.0 m",
                "6.0 m",
                "10.0 m"
            ),
            correctIndex = 2,
            explanation = "Since no external horizontal forces act on the two-skater system, their center of mass remains stationary: m₁ Δx₁ = m₂ Δx₂. With Δx₁ + Δx₂ = 10 m and m₁ = 40 kg, m₂ = 60 kg: 40 Δx₁ = 60 (10 - Δx₁) ⟹ 100 Δx₁ = 600 ⟹ Δx₁ = 6.0 m.",
            formulaTakeaway = "m₁ Δx₁ = m₂ Δx₂ | Distance moved is inversely proportional to mass"
        ),
        Question(
            id = 35,
            chapter = 3,
            chapterTitle = "Rotational Dynamics",
            topic = "Circular Disc",
            questionText = "The moment of inertia of a uniform circular disc of mass M and radius R about an axis in its plane passing through its diameter is:",
            options = listOf(
                "½ M R²",
                "¼ M R²",
                "¾ M R²",
                "M R²"
            ),
            correctIndex = 1,
            explanation = "By symmetry for a flat disc in the x-y plane, I_x = I_y = I_diameter. By the perpendicular axis theorem, I_z = I_x + I_y = 2 I_diameter. Since I_z = ½ M R², we have I_diameter = ½ I_z = ¼ M R².",
            formulaTakeaway = "I_disc_diameter = ¼ MR² | I_disc_perpendicular = ½ MR²"
        )
    )

    fun getQuestionsByChapter(chapter: Int): List<Question> {
        return questions.filter { it.chapter == chapter }
    }

    fun getDailyQuizQuestions(count: Int = 5): List<Question> {
        return questions.shuffled().take(count)
    }

    fun getFullMockQuestions(count: Int = 20): List<Question> {
        return questions.shuffled().take(minOf(count, questions.size))
    }
}
