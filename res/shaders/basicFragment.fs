#version 330

in vec2 texCoord0;


out vec4 fragColor;

uniform vec3 baseColor;
uniform sampler2D sampler;

void main()
{
    vec4 color = fragColor = vec4(baseColor, 1);
	vec4 textureColor = texture(sampler, texCoord0.xy);

    if(textureColor != vec4(0,0,0,0))
        color *= textureColor;
		
	fragColor = color;
}